package com.ar.moviezone.service;

import com.ar.moviezone.dto.UserCredentialsDTO;
import com.ar.moviezone.dto.UserDTO;
import com.ar.moviezone.exception.MovieZoneException;

public interface UserService {
	
	UserDTO userAuthentication(UserCredentialsDTO userCredentialsDTO) throws MovieZoneException;
	
	UserDTO getUserByEmailId(String emailId) throws MovieZoneException;      
	
	String registerNewUser(UserDTO userDTO) throws MovieZoneException;
}
