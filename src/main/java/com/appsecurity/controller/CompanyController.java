package com.appsecurity.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.appsecurity.entity.Company;
import com.appsecurity.request.AuthenticationRequest;
import com.appsecurity.request.CompanyRequest;
import com.appsecurity.response.CompanyResponse;
import com.appsecurity.service.CompanyService;
import com.appsecurity.utility.JwtUtil;

@RestController
public class CompanyController {

	@Autowired
	private CompanyService companyService;

	@Autowired
	private JwtUtil jwtUtil;

	@Autowired
	private AuthenticationManager authenticationManager;

	@PreAuthorize("hasRole('ADMIN')")
	@PostMapping("/add")
	public ResponseEntity<CompanyResponse> addingComapnyData(@RequestBody CompanyRequest companyRequest) {
		Company addCompany = companyService.addCompany(companyRequest);
		return ResponseEntity.ok(CompanyResponse.builder().isError(Boolean.FALSE).message("Data Added into Database..")
				.data(addCompany).build());
	}

	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/")
	public String welcome() {
		return "Hello Sandeep...!";
	}

	@PostMapping("/authenticate")
	public String generateToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));

		} catch (Exception e) {
			throw new Exception("Invalid username / password ");
		}
		return jwtUtil.generateToken(authenticationRequest.getUsername());

	}
}
