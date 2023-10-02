package com.ar.moviezone.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.moviezone.dto.UserCredentialsDTO;
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
	public UserDTO userAuthentication(UserCredentialsDTO userCredentialsDTO) throws MovieZoneException {
		UserDTO userDTO = null;
		Optional <User> userOp = userRepository.findById(userCredentialsDTO.getEmailId());
		User user = userOp.orElseThrow(()->new MovieZoneException("UserService.USER_NOT_AVAILABLE"));
		
		if (!userCredentialsDTO.getPassword().equals(user.getPassword())) {
			throw new MovieZoneException("UserService.INVALID_CREDENTIALS");
		}
		
		userDTO = new UserDTO();
		userDTO.setName(user.getName());
		userDTO.setEmailId(userCredentialsDTO.getEmailId());
		userDTO.setPassword(userCredentialsDTO.getPassword());
		userDTO.setAddress(user.getAddress());
		userDTO.setPhoneNumber(user.getPhoneNumber());
		
		return userDTO;
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
	public String registerNewUser(UserDTO userDTO) throws MovieZoneException {
		
		boolean userEmailIdNotPresent = userRepository.findById(userDTO.getEmailId().toLowerCase()).isEmpty();
//		checking if email already present with any other user
		
		boolean userPhoneNumberNotPresent = userRepository.findByPhoneNumber(userDTO.getPhoneNumber()).isEmpty();
//		checking if phone already present with any other user
		
		String registeredEmailId =null;
		
		if (userEmailIdNotPresent) {
			if (userPhoneNumberNotPresent) {
				User user = new User();
				user.setAddress(userDTO.getAddress());
				user.setEmailId(userDTO.getEmailId().toLowerCase());
				user.setName(userDTO.getName());
				user.setPassword(userDTO.getPassword());
				user.setPhoneNumber(userDTO.getPhoneNumber());
				user.setRole("App_user");
				userRepository.save(user);
				
				registeredEmailId = userDTO.getEmailId();
			} else {
				throw new MovieZoneException("UserService.PHONE_ALREADY_AVAILABLE");
			}
		} else {
			throw new MovieZoneException("UserService.EMAIL_ALREADY_AVAILABLE");
		}
		return registeredEmailId;
	}
}
