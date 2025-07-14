package org.example.tegabus.jwt;

import lombok.RequiredArgsConstructor;
import org.example.tegabus.dto.LoginRequestDto;
import org.example.tegabus.dto.RegisterRequestDto;
import org.example.tegabus.user.Role;
import org.example.tegabus.user.User;
import org.example.tegabus.user.UserRepository;
import org.example.tegabus.user.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserService userService;

    public User registerUser(RegisterRequestDto dto){
        if(!dto.getPassword().equals(dto.getConfirmPassword())){
            throw new RuntimeException("Passwords do not match");
        }
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
//        user.setRole(Role.ADMIN);
        user.setRole(Role.USER);
        return userService.registerUser(user);
    }
    public User registerAdmin(RegisterRequestDto dto){
        if(!dto.getPassword().equals(dto.getConfirmPassword())){
            throw new RuntimeException("Passwords do not match");
        }
        User user = new User();
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        user.setFirstName(dto.getFirstName());
        user.setLastName(dto.getLastName());
        user.setRole(Role.ADMIN);
//        user.setRole(Role.USER);
        user.setCountry(dto.getCountry());
        user.setPhoneNumber(dto.getPhoneNumber());
        return userService.registerUser(user);
    }
    public User loginUser(LoginRequestDto dto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
    }

}
