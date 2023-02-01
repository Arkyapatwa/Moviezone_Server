package com.ar.moviezone.service;

import com.ar.moviezone.dto.UserDTO;

public interface UserService {
	
	UserDTO userAuthentication(String emailId, String Password);
	
	UserDTO getUserByEmailId(String emailId);      
	
	String registerNewUser(UserDTO userDTO);
}
