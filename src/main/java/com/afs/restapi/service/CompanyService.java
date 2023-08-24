package com.afs.restapi.service;

import com.afs.restapi.entity.Company;
import com.afs.restapi.entity.Employee;
import com.afs.restapi.exception.CompanyNotFoundException;
import com.afs.restapi.repository.CompanyRepository;
import com.afs.restapi.repository.EmployeeRepository;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyService {
    private final CompanyRepository companyRepository;
    private final EmployeeRepository employeeRepository;

    public CompanyService(CompanyRepository companyRepository, EmployeeRepository employeeRepository) {
        this.companyRepository = companyRepository;
        this.employeeRepository = employeeRepository;
    }

    public List<Company> findAll() {
        return companyRepository.findAll();
    }

    public List<Company> findByPage(Integer pageNumber, Integer pageSize) {
        return companyRepository.findAll(PageRequest.of(pageNumber, pageSize)).toList();
    }

    public Company findById(Long id) {
        Company company = companyRepository.findById(id).orElseThrow(CompanyNotFoundException::new);
        return company;
    }

    public void update(Long id, Company company) {
        Company toBeUpdatedCompany = findById(id);
        toBeUpdatedCompany.setName(company.getName());
    }

    public Company create(Company company) {
        return companyRepository.save(company);
    }

    public List<Employee> findEmployeesByCompanyId(Long companyId) {
        return employeeRepository.findByCompanyId(companyId);
    }

    public void delete(Long id) {
        companyRepository.deleteById(id);
    }
}
