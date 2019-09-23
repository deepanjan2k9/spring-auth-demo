package com.example.spring.springauthdemo;

import java.util.Collections;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

@Configuration
@ComponentScan
public class CustomAuthenticationProvider implements AuthenticationProvider {
	
	@Override
	public Authentication authenticate(Authentication auth) throws AuthenticationException {
		
		//save username and password of Authentication object parameter to local variables 
		String username = auth.getName();
		String password = auth.getCredentials().toString();
		
		//compare given username and password with those stored
		if("externaluser".contentEquals(username) && "pass".equals(password)) {
			return new UsernamePasswordAuthenticationToken(username, password, Collections.emptyList());
		} else {
			throw new BadCredentialsException("External system authentication failed. ");	
		}
		
	}

	@Override
	public boolean supports(Class<?> auth) {
		
		return auth.equals(UsernamePasswordAuthenticationToken.class);
	}
}
