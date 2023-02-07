package com.ar.moviezone.service;

import com.ar.moviezone.dto.UserDTO;
import com.ar.moviezone.exception.MovieZoneException;

public interface UserService {
	
	UserDTO userAuthentication(String emailId, String Password);
	
	UserDTO getUserByEmailId(String emailId) throws MovieZoneException;      
	
	String registerNewUser(UserDTO userDTO) throws MovieZoneException;
}
