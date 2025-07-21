package org.example.tegabus.user;

import lombok.RequiredArgsConstructor;
import org.example.tegabus.EmailService;
import org.example.tegabus.analytics.UserAnalyticsResponse;
import org.example.tegabus.verificationToken.VerificationToken;
import org.example.tegabus.verificationToken.verificationTokenRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final EmailService emailService;
    private final verificationTokenRepository tokenRepository;

    public User getUserByEmail(String email){
        return userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public String createPasswordResetToken(String email){
        User user = getUserByEmail(email);
        user.setResetToken(UUID.randomUUID().toString());
        user.setResetTokenExpiry(LocalDateTime.now().plusMinutes(15));
        userRepository.save(user);
        return user.getResetToken();

    }

    public User getUserByResetToken(String token){
        return userRepository.findByResetToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid reset token"));
    }
    public void resetPassword(String token, String newPassword){
        User user = getUserByResetToken(token);
        if(user.getResetTokenExpiry().isBefore(LocalDateTime.now())){
            throw new RuntimeException("Reset token has expired");
        }
        user.setPassword(passwordEncoder.encode(newPassword));
        user.setResetToken(null);
        user.setResetTokenExpiry(null);
        userRepository.save(user);
    }
    public UserAnalyticsResponse getUserAnalytics(){
        long total = userRepository.count();
        long admin = userRepository.countByRole(Role.ADMIN);
        long user = userRepository.countByRole(Role.USER);
        long driver = userRepository.countByRole(Role.DRIVER);

        return new UserAnalyticsResponse(total, admin, user, driver);
    }

//    VERIFICATION

    public User registerUser(User user) {
        String gmailRegex = "^[a-zA-Z0-9](?!.*[._]{2})[a-zA-Z0-9._]{0,28}[a-zA-Z0-9]@gmail\\.com$";
        if (!user.getEmail().matches(gmailRegex)) {
            throw new IllegalArgumentException("Email must be a valid Gmail address like name@gmail.com");
        }


        if (userRepository.findByEmail(user.getEmail()).isPresent()) {
            throw new IllegalArgumentException("Email is already registered");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setVerified(false);
        User savedUser = userRepository.save(user);

        String token = UUID.randomUUID().toString();
        VerificationToken verificationToken = new VerificationToken(token,savedUser);
        tokenRepository.save(verificationToken);


        emailService.sendVerificationEmail(user.getEmail(), user.getVerificationToken());
        return savedUser;
    }

    public void verifyUserByToken(String token) {
        VerificationToken verificationToken = tokenRepository.findByToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid verification token"));

        if (verificationToken.getExpiryDate().isBefore(LocalDateTime.now())) {
            tokenRepository.delete(verificationToken);
            throw new RuntimeException("Verification token expired");
        }

        User user = verificationToken.getUser();
        user.setVerified(true);
        userRepository.save(user);

        tokenRepository.delete(verificationToken);

    }

    public void checkIfVerified(User user) {
        if (!user.isVerified()) {
            throw new RuntimeException("Please verify your email before logging in");
        }
    }

    public void deleteAdminById(UUID id) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!user.getRole().equals(Role.ADMIN)) {
            throw new RuntimeException("Cannot delete a user who is not an admin");
        }

        userRepository.delete(user);
    }
}
