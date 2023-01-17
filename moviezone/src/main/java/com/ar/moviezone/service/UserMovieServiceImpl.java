package com.ar.moviezone.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.moviezone.dto.MovieDTO;
import com.ar.moviezone.entity.Movie;
import com.ar.moviezone.repository.MovieRepository;

import jakarta.transaction.Transactional;

@Service("userMovieService")
@Transactional
public class UserMovieServiceImpl implements UserMovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Override
	public List<MovieDTO> getAllMovies(){
		Iterable<Movie> movies = movieRepository.findAll();
		List<MovieDTO> movieDTOs = new ArrayList<>();
		
		movies.forEach(movie->{
			MovieDTO movieDTO = new MovieDTO();
			
			movieDTO.setLanguage(movie.getLanguage());
			movieDTO.setMovieId(movie.getMovieId());
			movieDTO.setMovieLength(movie.getMovieLength());
			movieDTO.setMovieType(movie.getMovieType());
			movieDTO.setName(movie.getName());
			
			movieDTOs.add(movieDTO);
		});
		return movieDTOs;
	}
	
	@Override
	public MovieDTO getMovieById(Integer movieId) {
		return null;
	}
}
