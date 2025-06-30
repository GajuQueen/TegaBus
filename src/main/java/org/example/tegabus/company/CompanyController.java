package org.example.tegabus.company;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
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
    public ResponseEntity<Company> createCompany(@RequestBody @Valid CompanyDto dto){
        Company company = companyService.createCompany(dto);
        return new ResponseEntity<>(company, HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Get all companies")
    public ResponseEntity<List<Company>> getAllByCompany() {
        List<Company> company = companyService.getAllByCompany();
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Find Company by id")
    public ResponseEntity<Company> findBusById(@PathVariable UUID id){
        Company company = companyService.findBusById(id);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @PatchMapping("/{id}")
    @Operation(summary = "Update a company")
    public ResponseEntity<Company> updateCompany(@PathVariable UUID id, @RequestBody @Valid CompanyDto dto){
        Company company = companyService.updateBus(id, dto);
        return new ResponseEntity<>(company, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete Bus by id")
    public ResponseEntity<Void> deleteById(@PathVariable UUID id){
        companyService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
