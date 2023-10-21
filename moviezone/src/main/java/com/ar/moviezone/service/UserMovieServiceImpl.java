package com.ar.moviezone.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.moviezone.dto.MovieDTO;
import com.ar.moviezone.entity.Movie;
import com.ar.moviezone.exception.MovieZoneException;
import com.ar.moviezone.repository.MovieRepository;

import jakarta.transaction.Transactional;

@Service("userMovieService")
@Transactional
public class UserMovieServiceImpl implements UserMovieService {
	
	@Autowired
	private MovieRepository movieRepository;
	
	@Override
	public List<MovieDTO> getAllMovies() throws MovieZoneException{
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
	public MovieDTO getMovieById(Integer movieId) throws MovieZoneException {
		Optional<Movie> movieOp = movieRepository.findById(movieId);
		
		Movie movie = movieOp.orElseThrow(()->new MovieZoneException("UserMovieService.MOVIE_NOT_FOUND"));
		
		MovieDTO movieDTO = new MovieDTO();
		movieDTO.setLanguage(movie.getLanguage());
		movieDTO.setMovieId(movieId);
		movieDTO.setMovieLength(movie.getMovieLength());
		movieDTO.setName(movie.getName());
		movieDTO.setMovieType(movie.getMovieType());
		
		return movieDTO;
	}
	
	@Override
	public Integer addMovie(MovieDTO movieDTO) throws MovieZoneException {
		Movie movie = new Movie();
		movie.setLanguage(movieDTO.getLanguage());
		movie.setMovieLength(movieDTO.getMovieLength());
		movie.setName(movieDTO.getName());
		movie.setMovieType(movieDTO.getMovieType());
		
		movieRepository.save(movie);
		return movie.getMovieId();
	}
}
