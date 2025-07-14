package org.example.tegabus.user;

import lombok.RequiredArgsConstructor;
import org.example.tegabus.EmailService;
import org.example.tegabus.analytics.UserAnalyticsResponse;
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

    public void registerUser(User user) {
        if (!user.getEmail().endsWith("@gmail.com")) {
            throw new IllegalArgumentException("Only Gmail addresses are allowed");
        }

        user.setPassword(passwordEncoder.encode(user.getPassword()));

        String token = UUID.randomUUID().toString();
        user.setVerificationToken(token);
        user.setVerified(false);

        userRepository.save(user);
        emailService.sendVerificationEmail(user.getEmail(), token);
    }

    public void verifyUserByToken(String token) {
        User user = userRepository.findByVerificationToken(token)
                .orElseThrow(() -> new RuntimeException("Invalid verification token"));

        user.setVerified(true);
        user.setVerificationToken(null);
        userRepository.save(user);
    }

    public void checkIfVerified(User user) {
        if (!user.isVerified()) {
            throw new RuntimeException("Please verify your email before logging in");
        }
    }
}
