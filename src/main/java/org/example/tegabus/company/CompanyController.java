package org.example.tegabus.company;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.tegabus.company.Dtos.CompanyDto;
import org.example.tegabus.company.Dtos.CompanyResponseDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/companies")
@SecurityRequirement(name = "auth")
@RequiredArgsConstructor
public class CompanyController {
    private final CompanyService companyService;

    @PostMapping
    @Operation(summary = "Create a Company")
    public ResponseEntity<CompanyResponseDto> createCompany(@RequestBody @Valid CompanyDto dto){
        Company company = companyService.createCompany(dto);
        CompanyResponseDto responseDto = CompanyResponseDto.builder()
                .id(company.getId())
                .companyName(company.getCompanyName())
                .companyAddress(company.getCompanyAddress())
                .email(company.getEmail())
                .phoneNumber(company.getPhoneNumber())
                .status(company.getStatus())
        .build();
        return new ResponseEntity<>(responseDto, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all companies")
    public ResponseEntity<List<CompanyResponseDto>> getAllByCompany() {
        List<Company> companies = companyService.getAllByCompany();
        List<CompanyResponseDto> responseDto = companies.stream()
                .map(company -> CompanyResponseDto.builder()
                .id(company.getId())
                .companyName(company.getCompanyName())
                .companyAddress(company.getCompanyAddress())
                .email(company.getEmail())
                .phoneNumber(company.getPhoneNumber())
                .status(company.getStatus())
                .build())
         .toList();
        return new ResponseEntity<>( responseDto, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find Company by id")
    public ResponseEntity<CompanyResponseDto> findBusById(@PathVariable UUID id){
        Company company = companyService.findBusById(id);
        CompanyResponseDto responseDto = CompanyResponseDto.builder()
                .id(company.getId())
                .companyName(company.getCompanyName())
                .companyAddress(company.getCompanyAddress())
                .email(company.getEmail())
                .phoneNumber(company.getPhoneNumber())
                .status(company.getStatus())
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @SecurityRequirement(name = "auth")
    @PatchMapping("/{id}")
    @Operation(summary = "Update a company")
    public ResponseEntity<CompanyResponseDto> updateCompany(@PathVariable UUID id, @RequestBody @Valid CompanyDto dto){
        Company company = companyService.updateBus(id, dto);
        CompanyResponseDto responseDto = CompanyResponseDto.builder()
                .id(company.getId())
                .companyName(company.getCompanyName())
                .companyAddress(company.getCompanyAddress())
                .email(company.getEmail())
                .phoneNumber(company.getPhoneNumber())
                .status(company.getStatus())
                .build();
        return new ResponseEntity<>(responseDto, HttpStatus.OK);
    }

    @SecurityRequirement(name = "auth")
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Bus by id")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        companyService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
