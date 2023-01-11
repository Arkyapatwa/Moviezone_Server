package com.ar.moviezone.repository;

import org.springframework.data.repository.CrudRepository;

import com.ar.moviezone.entity.User;

public interface UserRepository extends CrudRepository<User, Integer> {

}
