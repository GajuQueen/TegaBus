
package org.example.tegabus.user;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.tegabus.analytics.UserAnalyticsResponse;
import org.example.tegabus.dto.LoginRequestDto;
import org.example.tegabus.dto.PasswordResetDto;
import org.example.tegabus.dto.RegisterRequestDto;
import org.example.tegabus.jwt.AuthService;
import org.example.tegabus.jwt.JwtService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/api/auth/")
@SecurityRequirement(name = "auth")
@RequiredArgsConstructor
public class UserController {
    private final UserRepository userRepository;
    private final AuthService authService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody @Valid LoginRequestDto dto) {
        var loggedInUser = authService.loginUser(dto);
        String token = jwtService.generateToken(loggedInUser);
        Map<String, String> response = new HashMap<>();
        response.put("token", token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/register")
    public ResponseEntity<User> register(@RequestBody @Valid RegisterRequestDto dto){
        var registerUser = authService.registerUser(dto);
        return new ResponseEntity<>(registerUser, HttpStatus.OK);
    }
    @PostMapping("/register/admin")
    public ResponseEntity<User> registerAdmin(@RequestBody @Valid RegisterRequestDto dto){
        var registerAdmin = authService.registerAdmin(dto);
        return new ResponseEntity<>(registerAdmin, HttpStatus.OK);
    }
    @GetMapping("/verify-email")
    @Operation(summary = "Verify user email using token from email")
    public ResponseEntity<String> verifyEmail(@RequestParam("token") String token) {
        userService.verifyUserByToken(token);
        return ResponseEntity.ok("Email verified successfully! You may now login.");
    }


    @PostMapping("/forgot-password")
    public ResponseEntity<?> forgotPassword(@RequestBody @Valid String email){
        User user =  userRepository.findByEmail(email).orElseThrow(() -> new RuntimeException("Email not found"));
        String token = UUID.randomUUID().toString();
        user.setResetToken(token);
        user.setResetTokenExpiry(LocalDateTime.now().plusMinutes(15));
        userRepository.save(user);
        String link = "htttp://localhost:8080/reset-password?token=" + token;
        System.out.println("Reset link: " + link);
//        userService.createPasswordResetToken(email);
        return ResponseEntity.ok("Password reset link sent to your email.");
    }


    @PostMapping("/reset-password")
    public ResponseEntity<?> resetPassword(@RequestBody @Valid PasswordResetDto reset){
        User user = userRepository.findByResetToken(reset.getToken()).orElseThrow(() -> new RuntimeException("Invalid token"));
        if(user.getResetTokenExpiry().isBefore(LocalDateTime.now())){
            throw new RuntimeException("Token is expired");
        }
        user.setPassword(passwordEncoder.encode(reset.getNewPassword()));
        user.setResetToken(null);
        user.setResetTokenExpiry(null);
        userRepository.save(user);
//        userService.resetPassword(token, newPassword);
        return ResponseEntity.ok("Password reset successfully .");
    }


    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/analytics")
    @Operation(summary = "Get user analytics ")
    public ResponseEntity<UserAnalyticsResponse> getUserAnalytics(){
        return new ResponseEntity<>(userService.getUserAnalytics(), HttpStatus.OK);
    }

    @DeleteMapping("/admin/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    @Operation(summary = "Delete an admin by ID")
    public ResponseEntity<String> deleteAdmin(@PathVariable("id") UUID id) {
        userService.deleteAdminById(id);
        return ResponseEntity.ok("Admin deleted successfully.");
    }

}

