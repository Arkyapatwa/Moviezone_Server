package com.ar.moviezone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ar.moviezone.service.UserMovieService;

@RestController
@RequestMapping("/movie-api")
public class MovieController {
		
		@Autowired
		private UserMovieService userMovieService;
		
		@Autowired
		private Environment environment;
}
