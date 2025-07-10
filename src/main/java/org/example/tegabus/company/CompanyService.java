package org.example.tegabus.company;

import lombok.RequiredArgsConstructor;
import org.example.tegabus.company.Dtos.CompanyDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CompanyService {
    public final CompanyRepository companyRepository;

    public Company createCompany(CompanyDto dto) {
        Company company = new Company();
        company.setCompanyName(dto.getCompanyName());
        company.setCompanyAddress(dto.getCompanyAddress());
        company.setEmail(dto.getEmail());
        company.setPhoneNumber(dto.getPhoneNumber());
        company.setStatus(dto.getStatus());
        company.setRegistration(dto.getRegistration());
        return companyRepository.save(company);

    }

        public List<Company> getAllByCompany(){
        return companyRepository.findAll();
        }

    public Company findBusById(UUID id) {
        return companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
    }

    public Company updateBus(UUID id, CompanyDto dto) {

        Company company = companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        company.setCompanyName(dto.getCompanyName());
        company.setCompanyAddress(dto.getCompanyAddress());
        company.setEmail(dto.getEmail());
        company.setPhoneNumber(dto.getPhoneNumber());
        company.setStatus(dto.getStatus());
        company.setRegistration(dto.getRegistration());
        return companyRepository.save(company);

    }

    public void deleteById(UUID id) {
        companyRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        companyRepository.deleteById(id);
    }
}
