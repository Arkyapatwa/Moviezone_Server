package com.ar.moviezone.service;

import java.util.List;

import com.ar.moviezone.dto.BookingDTO;
import com.ar.moviezone.dto.CardDTO;
import com.ar.moviezone.dto.MovieDTO;
import com.ar.moviezone.dto.PaymentDTO;
import com.ar.moviezone.exception.MovieZoneException;

public interface UserBookingService {
	
	Integer bookMovie(String emailId, PaymentDTO paymentDTO, CardDTO cardDTO, MovieDTO movieDTO) throws MovieZoneException;
	
	List<BookingDTO> findBookingByUserEmailId(String emailId) throws MovieZoneException;
	
	BookingDTO getBookingById(Integer bookingId) throws MovieZoneException;
}
