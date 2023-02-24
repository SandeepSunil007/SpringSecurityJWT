package com.appsecurity.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class CompanyRequest {
	private long companyId;
	private String companyName;
	private String area;
	private String username;
	private String password;
	private String roles;
}
