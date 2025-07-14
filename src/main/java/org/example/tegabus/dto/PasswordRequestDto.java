package org.example.tegabus.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class PasswordRequestDto {
    @NotBlank(message = "Email is required")
    @Email(message = "Invalid email format")
    @Pattern(regexp = "^[\\w.+\\-]+@gmail\\.com$", message = "Only Gmail addresses are allowed")
    private String email;
}
