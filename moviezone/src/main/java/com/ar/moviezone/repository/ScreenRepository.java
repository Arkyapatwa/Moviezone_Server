package com.ar.moviezone.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.ar.moviezone.entity.Screen;

public interface ScreenRepository extends CrudRepository<Screen, Integer>{

	List<Screen> findByTheatreId(Integer theatreId);
}
