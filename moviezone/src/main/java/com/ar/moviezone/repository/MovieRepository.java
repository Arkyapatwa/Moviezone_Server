package com.ar.moviezone.repository;

import org.springframework.data.repository.CrudRepository;

import com.ar.moviezone.entity.Movie;

public interface MovieRepository extends CrudRepository<Movie, Integer> {

}
