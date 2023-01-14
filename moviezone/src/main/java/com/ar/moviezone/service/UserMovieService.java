package com.ar.moviezone.service;

import java.util.List;

import com.ar.moviezone.dto.MovieDTO;
import com.ar.moviezone.entity.Movie;

public interface UserMovieService {
	
	List<MovieDTO> getAllMovies();
	
	MovieDTO getMovieById(Integer movieId);
}
