package com.example.company.service;

import com.example.company.dao.CompanyEntity;
import com.example.company.dao.CompanyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyService {
    private CompanyRepository companyRepository;

    public CompanyService(CompanyRepository companyRepository){
        this.companyRepository = companyRepository;
    }

    public List<Company> getAllCompanies(){
        return companyRepository.findAll().stream().map(CompanyEntity::toPojo).collect(Collectors.toList());
    }

    public Company addCompany(Company company){
        CompanyEntity newCompany = new CompanyEntity();
        newCompany.update(company);
        return companyRepository.save(newCompany).toPojo();
    }

    public Company updateCompany(String existingName, Company company){
        CompanyEntity saved = companyRepository.findCompanyByName(existingName).orElseThrow(() -> new CompanyNotFoundException(company.getName()));
        saved.update(company);
        return companyRepository.save(saved).toPojo();
    }
    public Company getById(int id){
        return companyRepository.findById(id).orElseThrow(() -> new CompanyNotFoundException(String.valueOf(id))).toPojo();
    }
    public void deleteByName(String existingName){
        companyRepository.deleteByName(existingName);
    }
