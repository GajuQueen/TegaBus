package org.example.tegabus.company.Dtos;



import lombok.Builder;
import lombok.Data;
import org.example.tegabus.company.CompanyStatus;

import java.util.UUID;

@Data
@Builder
public class CompanyResponseDto {
    private UUID id;
    private String companyName;
    private String companyAddress;
    private String email;
    private String phoneNumber;
    private CompanyStatus status;

}
