package com.ar.moviezone.controller;

import java.util.List;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ar.moviezone.dto.BookingDTO;
import com.ar.moviezone.exception.MovieZoneException;
import com.ar.moviezone.service.UserBookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {
	
	@Autowired
	private UserBookingService userBookingService;
	
	@Autowired
	private Environment environment;
	
	@GetMapping("/movies")
	public ResponseEntity<List<BookingDTO>> getBookingByEmailId(String emailId) throws MovieZoneException {
		List<BookingDTO> bookingDTOList = userBookingService.findBookingByUserEmailId(emailId);
		return new ResponseEntity<>(bookingDTOList, HttpStatus.OK);
	}
	
}
