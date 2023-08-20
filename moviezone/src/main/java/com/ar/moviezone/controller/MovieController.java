package com.ar.moviezone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ar.moviezone.dto.MovieDTO;
import com.ar.moviezone.exception.MovieZoneException;
import com.ar.moviezone.service.UserMovieService;

@RestController
@RequestMapping("/movie-api")
public class MovieController {
		
		@Autowired
		private UserMovieService userMovieService;
		
		@Autowired
		private Environment environment;
		
		@GetMapping("/movies")
		public ResponseEntity<List<MovieDTO>> getAllMovies() throws MovieZoneException {
			List<MovieDTO> movieDTOList = userMovieService.getAllMovies();
			return new ResponseEntity<>(movieDTOList, HttpStatus.OK);
		}
		
		@GetMapping("/movie/{movieId}")
		public ResponseEntity<MovieDTO> getMovieById(@PathVariable Integer movieId) throws MovieZoneException {
			MovieDTO movieDTO = userMovieService.getMovieById(movieId);
			return new ResponseEntity<>(movieDTO, HttpStatus.OK);
		}
}
