package com.appsecurity.security.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.appsecurity.filter.JwtFilter;
import com.appsecurity.service.CompanyUserDetailsService;

@Configuration
@EnableWebSecurity
public class CompanySecurity extends WebSecurityConfigurerAdapter {
//	SecurityFilterChain chain(HttpSecurity httpSecurity) throws Exception {
//		httpSecurity.csrf().disable().authorizeHttpRequests((x) -> x.anyRequest().authenticated())
//				.httpBasic(Customizer.withDefaults());
//		return httpSecurity.build();
//	}
	@Autowired
	private CompanyUserDetailsService companyUserDetailsService;

	@Autowired
	JwtFilter filter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(companyUserDetailsService);
	}

	@Bean
	public PasswordEncoder encoder() {
		return NoOpPasswordEncoder.getInstance();
	}

	@Bean(name = BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.csrf().disable()
		           .authorizeRequests()
		           .antMatchers("/authenticate")
		           .permitAll()
		           .anyRequest()
		           .authenticated()
				   .and()
				   .exceptionHandling()
				   .and()
				   .sessionManagement()
				   .sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);
		// role based authentication
		
	}
//
//	http.csrf().disable();
//	http.authorizeRequests()
//	.antMatchers("/admin/**").hasRole("ADMIN")
//	.antMatchers("/mentor/**").hasRole("MENTOR")
//	.antMatchers("/employee/**").hasRole("EMPLOYEE")
//	.antMatchers("/register/employeegft").permitAll()
//	.anyRequest().authenticated().and().formLogin();

}
