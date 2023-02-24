package com.appsecurity.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.appsecurity.entity.Company;
import com.appsecurity.repository.CompanyRepository;

@Service
public class CompanyUserDetailsService implements UserDetailsService {
	@Autowired
	private CompanyRepository companyRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Company findByUsername = companyRepository.findByUsername(username);
		return new User(findByUsername.getUsername(), findByUsername.getPassword(), new ArrayList<>());
	}

}
