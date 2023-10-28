package com.ar.moviezone.controller;

import java.util.List;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ar.moviezone.dto.BookingDTO;
import com.ar.moviezone.dto.CardDTO;
import com.ar.moviezone.dto.MovieDTO;
import com.ar.moviezone.dto.PaymentDTO;
import com.ar.moviezone.exception.MovieZoneException;
import com.ar.moviezone.service.UserBookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {
	
	@Autowired
	private UserBookingService userBookingService;
	
	@Autowired
	private Environment environment;
	
	@GetMapping("/getAllBookings/{emailId}")
	public ResponseEntity<List<BookingDTO>> getBookingByEmailId(@PathVariable("emailId") String emailId) throws MovieZoneException {
		List<BookingDTO> bookingDTOList = userBookingService.findBookingByUserEmailId(emailId);
		return new ResponseEntity<>(bookingDTOList, HttpStatus.OK);
	}
	
	@PostMapping("/bookMovie/{emailId}")
	public ResponseEntity<Integer> bookMovie(@PathVariable("emailId") String emailId, @RequestBody PaymentDTO paymentDTO,  CardDTO cardDTO, MovieDTO movieDTO) throws MovieZoneException {
		Integer id = userBookingService.bookMovie(emailId, paymentDTO, cardDTO, movieDTO);
		return new ResponseEntity<>(id, HttpStatus.OK);
	}
	
	@GetMapping("/bookMovie/{bookingId}")
	public ResponseEntity<BookingDTO> getBookingById(@PathVariable("bookingId") Integer bookingId) throws MovieZoneException {
		BookingDTO bookingDTO = userBookingService.getBookingById(bookingId);
		return new ResponseEntity<>(bookingDTO, HttpStatus.OK);
	}
	
}
