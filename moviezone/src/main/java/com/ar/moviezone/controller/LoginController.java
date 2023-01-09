package com.ar.moviezone.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class LoginController {

	
	@GetMapping("/login")
	public String login()
	{
		System.out.println("Inside login controller");
		return "login page rendered";
	}

}
