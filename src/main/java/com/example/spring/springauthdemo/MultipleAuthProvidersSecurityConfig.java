package com.example.spring.springauthdemo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

// consider adding annotation @Configuration here...
@Configuration
@EnableWebSecurity
public class MultipleAuthProvidersSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CustomAuthenticationProvider customAuthProvider;
	
	/**
	 * This is where credentials and roles for authenticated users are configured
	 * */
	
	@Override
	public void configure(AuthenticationManagerBuilder authManagerBuilder) throws Exception {
		//custom and in-memory authentication providers to get credentials from a DB
		//authManagerBuilder.authenticationProvider(customAuthProvider);
		
		//in-memory authentication allows us to provide username and password here itself, along
		//with their roles.
		authManagerBuilder.inMemoryAuthentication()
		//create two users, one of type USER and the other of type ADMIN
		.withUser("memuser")
		.password(passwordEncoder().encode("memuserpass")).roles("USER").and()
		.withUser("deepa").password(passwordEncoder().encode("admin123")).roles("ADMIN");
		//password encoder utility in Spring Security
		
	}
	
	/**
	 * This is where the authentication and authorization for incoming requests are configured 
	 * */
	
	@Override
	public void configure(HttpSecurity httpSec) throws Exception {
		//httpSec.httpBasic().and().authorizeRequests().antMatchers("/api/**").authenticated();
		httpSec
		//provide basic HTTP authentication
		.httpBasic().and()
		//authorize all incoming requests
		.authorizeRequests()
		//for specific URL matching ../rest/.. pattern
		.antMatchers("/rest/**")
		//make sure incoming requests are authenticated
		.authenticated();
	}
	
	/**
	 * Utility password encoder in Spring Security that encodes a given string
	 * */
	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
