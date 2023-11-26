package com.ar.moviezone.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ar.moviezone.dto.ScreenDTO;
import com.ar.moviezone.exception.MovieZoneException;
import com.ar.moviezone.service.ScreenService;

@RestController
@RequestMapping("/screen")
public class ScreenController {

	@Autowired
	private ScreenService screenService;
	
	@PostMapping("/addScreen")
	public ResponseEntity<String> addScreen(@RequestBody ScreenDTO screenDTO) throws MovieZoneException {
		String response = screenService.addScreen(screenDTO);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@GetMapping("/getScreens/{theatreId}")
	public ResponseEntity<List<ScreenDTO>> getAllScreen(@PathVariable("theatreId") Integer TheatreId) throws MovieZoneException {
		List<ScreenDTO> screenDTOList = screenService.getAllScreens(TheatreId);
		return new ResponseEntity<>(screenDTOList, HttpStatus.OK);
	}
	
	@GetMapping("/getScreen/{screenId}")
	public ResponseEntity<ScreenDTO> getScreen(@PathVariable("screenId") Integer screenId) throws MovieZoneException {
		ScreenDTO screenDTO = screenService.getScreenById(screenId);
		return new ResponseEntity<>(screenDTO, HttpStatus.OK);
	}
	
	
}
