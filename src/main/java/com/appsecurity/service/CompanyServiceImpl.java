package com.appsecurity.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.appsecurity.entity.Company;
import com.appsecurity.repository.CompanyRepository;
import com.appsecurity.request.CompanyRequest;

@Service
public class CompanyServiceImpl implements CompanyService {

	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public Company addCompany(CompanyRequest companyRequest) {
		Company company = Company.builder()
				.companyName(companyRequest.getCompanyName())
				.area(companyRequest.getArea())
				.username(companyRequest.getUsername())
				.password(companyRequest.getPassword())
				.roles(companyRequest.getRoles())
				.build();	
		Company save = companyRepository.save(company);
		return save;
	}

}
