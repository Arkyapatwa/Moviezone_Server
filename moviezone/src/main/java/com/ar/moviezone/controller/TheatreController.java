package com.ar.moviezone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ar.moviezone.dto.TheatreDTO;
import com.ar.moviezone.exception.MovieZoneException;
import com.ar.moviezone.service.TheatreService;

@RestController
@RequestMapping("/theatre")
public class TheatreController {

	@Autowired
	private TheatreService theatreService;
	
	@PostMapping("/addTheatre")
	public ResponseEntity<String> addTheatre(@RequestBody TheatreDTO theatreDTO) throws MovieZoneException {
		String response = theatreService.addTheatre(theatreDTO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
