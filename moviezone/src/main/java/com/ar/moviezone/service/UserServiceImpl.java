package com.ar.moviezone.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.moviezone.dto.UserDTO;
import com.ar.moviezone.entity.User;
import com.ar.moviezone.exception.MovieZoneException;
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
	public UserDTO getUserByEmailId(String emailId) throws MovieZoneException{
		Optional<User> userOp = userRepository.findById(emailId);
		User user = userOp.orElseThrow(()-> new MovieZoneException("UserService.USER_NOT_AVAILABLE"));
		
		UserDTO userDTO = new UserDTO();
		userDTO.setAddress(user.getAddress());
		userDTO.setEmailId(emailId);
		userDTO.setName(user.getName());
		userDTO.setPassword(user.getPassword());
		userDTO.setPhoneNumber(user.getPhoneNumber());
		
		return userDTO;
	}
	
	@Override
	public String registerNewUser(UserDTO userDTO) {
		return null;
	}
}
