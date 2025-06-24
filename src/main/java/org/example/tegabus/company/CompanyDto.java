package org.example.tegabus.company;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyDto {
    @NotBlank(message = "CompanyName is Required")
    private String companyName;
    @NotBlank(message = "CompanyAddress is Required")
    private String companyAddress;
    @Email(message = "Email must be valid")
    private String email;
    @Pattern(regexp = "^\\+?[0-9]{9,15}$", message = "Phone number must be valid")
    private String phoneNumber;
    @NotNull(message = "Status is required")
    private CompanyStatus status;

}
