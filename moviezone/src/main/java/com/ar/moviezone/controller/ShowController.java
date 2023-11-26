package com.ar.moviezone.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ar.moviezone.dto.ShowDTO;
import com.ar.moviezone.exception.MovieZoneException;
import com.ar.moviezone.service.ShowService;

@RestController
@RequestMapping("/show")
public class ShowController {

	@Autowired
	private ShowService showService;
	
	@PostMapping("/addShow")
	public ResponseEntity<String> addShow(@RequestBody ShowDTO showDTO) throws MovieZoneException {
		String response = showService.addShow(showDTO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
