package com.example.company.controller;

import com.example.company.service.Company;
import com.example.company.service.CompanyDto;
import com.example.company.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/company")
public class CompanyController {

    private CompanyService companyService;

    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/")
    public List<CompanyDto> getAllCompanies() {
        return companyService.getAllCompanies().stream().map(Company::toDto).collect(Collectors.toList());

    }

    @PostMapping("/")
    public CompanyDto addCompany(@RequestBody Company company) {
        return companyService.addCompany(company).toDto();
    }

    @PutMapping("/{existingName}")
    public CompanyDto updateCompany(@PathVariable("existingName") String existingName, @RequestBody Company company) {
        return companyService.updateCompany(existingName, company).toDto();
    }

    @DeleteMapping("/{existingName}")
    @Transactional
    public void deleteCompanyById(@PathVariable String existingName) {
        companyService.deleteByName(existingName);
    }
}