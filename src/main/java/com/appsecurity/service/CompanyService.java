package com.appsecurity.service;

import com.appsecurity.entity.Company;
import com.appsecurity.request.CompanyRequest;

public interface CompanyService {

	public Company addCompany(CompanyRequest companyRequest);

}
