package com.ar.moviezone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ar.moviezone.dto.UserDTO;
import com.ar.moviezone.exception.MovieZoneException;
import com.ar.moviezone.service.UserService;

@RestController
@RequestMapping("/moviezone")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Environment environment;
	
	@PostMapping("/register")
	public ResponseEntity<String> resgisterUser(@RequestBody UserDTO userDTO) throws MovieZoneException{
		String registerEmailId = userService.registerNewUser(userDTO);
		registerEmailId = environment.getProperty("") + registerEmailId;
		return new ResponseEntity<>(registerEmailId, HttpStatus.OK);
	}
}
