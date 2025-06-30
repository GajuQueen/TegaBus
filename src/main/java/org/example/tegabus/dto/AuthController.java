//package org.example.tegabus.dto;
//
//import lombok.RequiredArgsConstructor;
//import org.example.tegabus.jwt.AuthService;
//import org.example.tegabus.jwt.JwtService;
//import org.example.tegabus.user.User;
//import org.example.tegabus.user.UserRepository;
//import org.example.tegabus.user.UserService;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//@RestController
//@RequestMapping("/api/auth/")
//@RequiredArgsConstructor
//public class AuthController {
//    private final AuthService authService;
//    private final JwtService jwtService;
//    private final UserService userService;
//
//    @PostMapping("/login")
//    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequestDto dto) {
//        var loggedInUser = authService.loginUser(dto);
//        String token = jwtService.generateToken(loggedInUser);
//        Map<String, String> response = new HashMap<>();
//        response.put("token", token);
//        return ResponseEntity.ok(response);
//    }
//
//    @PostMapping("/register")
//    public ResponseEntity<User> register(@RequestBody RegisterRequestDto dto){
//        var registerUser = authService.registerUser(dto);
//        return new ResponseEntity<>(registerUser, HttpStatus.OK);
//    }
//    @PostMapping("/forgot-password")
//    public ResponseEntity<?> forgotPassword(@RequestBody String email){
//        userService.createPasswordResetToken(email);
//        return ResponseEntity.ok("Password reset token generated.");
//    }
//    @PostMapping("/reset-password")
//    public ResponseEntity<?> resetPassword(@RequestBody String token, @RequestParam String newPassword){
//        userService.resetPassword(token, newPassword);
//        return ResponseEntity.ok("Password successfully updated.");
//    }
//}
