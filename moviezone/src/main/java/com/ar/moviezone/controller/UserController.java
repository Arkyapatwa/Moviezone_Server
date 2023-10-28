package com.ar.moviezone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ar.moviezone.dto.UserCredentialsDTO;
import com.ar.moviezone.dto.UserDTO;
import com.ar.moviezone.exception.MovieZoneException;
import com.ar.moviezone.service.UserService;

@Validated
@RestController
@RequestMapping("/moviezone")
public class UserController {
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private Environment environment;
	
	@PostMapping("/user/login")
	public ResponseEntity<UserDTO> authenticateUser(@RequestBody UserCredentialsDTO userCredDTO) throws MovieZoneException {
		UserDTO userDTOfetched = userService.userAuthentication(userCredDTO);
		return new ResponseEntity<>(userDTOfetched, HttpStatus.OK);
	}
	
	@GetMapping("/user/{emailId}")
	public ResponseEntity<UserDTO> getUserByEmailId(@PathVariable("emailId") String userEmailId) throws MovieZoneException {
		UserDTO userDTOfetched = userService.getUserByEmailId(userEmailId);
		return new ResponseEntity<>(userDTOfetched, HttpStatus.OK);
	}
	
	@PostMapping("/user/register")
	public ResponseEntity<String> resgisterUser(@RequestBody UserDTO userDTO) throws MovieZoneException{
		String registerEmailId = userService.registerNewUser(userDTO);
		return new ResponseEntity<>(registerEmailId, HttpStatus.OK);
	}
	
	
}
