package com.example.spring.springauthdemo.resources;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/rest/hello")
public class HelloResource {
	
	//set response to the URL ../rest/hello as the string "Hello World!"
	@GetMapping
	public String hello() {
		return "Hello World!";
	}
	
}
