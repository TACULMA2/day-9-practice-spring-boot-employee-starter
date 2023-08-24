package com.afs.restapi.service;

import com.afs.restapi.entity.Company;
import com.afs.restapi.entity.Employee;
import com.afs.restapi.exception.CompanyNotFoundException;
import com.afs.restapi.repository.CompanyJpaRepository;
import com.afs.restapi.repository.EmployeeJpaRepository;
import com.afs.restapi.repository.InMemoryCompanyRepository;
import com.afs.restapi.repository.InMemoryEmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {
    private final CompanyJpaRepository companyJpaRepository;
    private final EmployeeJpaRepository employeeJpaRepository;
    private final InMemoryCompanyRepository inMemoryCompanyRepository;
    private final InMemoryEmployeeRepository inMemoryEmployeeRepository;

    public CompanyService(CompanyJpaRepository companyJpaRepository, EmployeeJpaRepository employeeJpaRepository, InMemoryCompanyRepository inMemoryCompanyRepository, InMemoryEmployeeRepository inMemoryEmployeeRepository) {
        this.companyJpaRepository = companyJpaRepository;
        this.employeeJpaRepository = employeeJpaRepository;
        this.inMemoryCompanyRepository = inMemoryCompanyRepository;
        this.inMemoryEmployeeRepository = inMemoryEmployeeRepository;
    }

    public InMemoryCompanyRepository getCompanyRepository() {
        return inMemoryCompanyRepository;
    }

    public InMemoryEmployeeRepository getEmployeeRepository() {
        return inMemoryEmployeeRepository;
    }

    public List<Company> findAll() {
        return companyJpaRepository.findAll();
    }

    public List<Company> findByPage(Integer pageNumber, Integer pageSize) {
        return companyJpaRepository.findAll(PageRequest.of(pageNumber, pageSize)).toList();
    }

    public Company findById(Long id) {
        Company company = companyJpaRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
        return company;
    }

    public void update(Long id, Company company) {
        Company toBeUpdatedCompany = findById(id);
        toBeUpdatedCompany.setName(company.getName());
    }

    public Company create(Company company) {
        return companyJpaRepository.save(company);
    }

    public Optional<Employee> findEmployeesByCompanyId(Long id) {
        return employeeJpaRepository.findById(id);
    }

    public void delete(Long id) {
        companyJpaRepository.deleteById(id);
    }
}
