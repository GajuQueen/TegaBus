package org.example.tegabus.jwt;

import lombok.RequiredArgsConstructor;
import org.example.tegabus.dto.LoginRequestDto;
import org.example.tegabus.dto.RegisterRequestDto;
import org.example.tegabus.user.UserRepository;
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

    public UserRepository registerUser(RegisterRequestDto dto){
        UserRepository user = new UserRepository();
        user.setEmail(dto.getEmail());
        user.setPassword(passwordEncoder.encode(dto.getPassword()));
        user.setFullName(dto.getFullName());
        return userRepository.save(user);
    }
    public UserRepository loginUser(LoginRequestDto dto){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(dto.getEmail(), dto.getPassword())
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return userRepository.findByEmail(dto.getEmail()).orElseThrow(() -> new RuntimeException("User not found"));
    }

}
