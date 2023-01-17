package com.ar.moviezone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.moviezone.dto.UserDTO;
import com.ar.moviezone.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service("userService")
@Transactional
public class UserServiceImpl implements UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	@Override
	public UserDTO userAuthentication(String emailId, String Password) {
		return null;
	}
	
	@Override
	public UserDTO getUserByEmailId(String emailId) {
		return null;
	}
	
	@Override
	public String registerNewUser(UserDTO userDTO) {
		return null;
	}
}
