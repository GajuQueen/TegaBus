package org.example.tegabus.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class RegisterRequestDto {
    private String fullName;
    @NotBlank
    private String email;
    @NotBlank
    private String password;
}
