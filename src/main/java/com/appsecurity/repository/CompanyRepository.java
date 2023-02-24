package com.appsecurity.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.appsecurity.entity.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {

	Company findByUsername(String username);

}