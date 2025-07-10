package org.example.tegabus.jwt;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
public class SecurityConfig {
    private final JwtShield jwtShield;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(
                        customizer -> customizer
                                .requestMatchers(HttpMethod.GET, "/api/schedules").permitAll()
                                .requestMatchers(HttpMethod.GET, "/api/routes").permitAll()
                                .requestMatchers("/api/users/analytics").hasRole("ADMIN")
                                                .requestMatchers("/api/bookings/**").hasAnyRole("USER","ADMIN")
                                .requestMatchers("/api/schedules/**").hasAnyRole("DRIVER","USER")
                                .requestMatchers("/api/users/**").hasRole("ADMIN")
                                .requestMatchers(

                                        "/api/auth/register",
                                        "/auth/register",
                                        "/",
                                        "/api/auth/login",
                                        "/auth/login",
                                        "/api/auth/forgot-password",
                                        "/api/auth/reset-password",
                                        "/",
                                        "/v2/api-docs",
                                        "/v3/api-docs",
                                        "/v3/api-docs/**",
                                        "/swagger-resources/**",
                                        "/configuration/ui",
                                        "/configuration/security",
                                        "/swagger-ui/**",
                                        "/webjars/**",
                                        "/swagger-ui.html"

                                ).permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .sessionManagement(
                        customizer -> customizer.sessionCreationPolicy(
                                SessionCreationPolicy.STATELESS
                        )
                )
                .addFilterBefore(jwtShield, UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }
}
