package com.ar.moviezone.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ar.moviezone.entity.User;

public interface UserRepository extends CrudRepository<User, String> {
	
	List<User> findByPhoneNumber(String phoneNumber);
}
