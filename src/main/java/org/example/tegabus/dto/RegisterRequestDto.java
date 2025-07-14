package org.example.tegabus.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterRequestDto {
    @NotBlank
    private String firstName;
    @NotBlank
    private String lastName;
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Pattern(
            regexp = "^[a-zA-Z0-9](?!.*[._]{2})[a-zA-Z0-9._]{0,28}[a-zA-Z0-9]@gmail\\.com$",
            message = "Only valid Gmail addresses are allowed (e.g. name123@gmail.com)"
    )
    private String email;
    @NotBlank(message = "Password is required")
    @Size(min = 6, message = "Password must be at least 6 characters")
    private String password;
    @NotBlank
    private String confirmPassword;
    @NotBlank
    private String country;
    @Pattern(regexp = "^(\\+250|0)?7\\d{8}$", message = "Invalid Rwandan phone number")
    private String phoneNumber;
}
