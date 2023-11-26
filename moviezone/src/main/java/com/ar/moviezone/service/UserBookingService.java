package com.ar.moviezone.service;

import java.util.List;
import java.util.Map;

import com.ar.moviezone.dto.BookingDTO;
import com.ar.moviezone.dto.CardDTO;
import com.ar.moviezone.dto.PaymentDTO;
import com.ar.moviezone.dto.ShowDTO;
import com.ar.moviezone.exception.MovieZoneException;

public interface UserBookingService {
	
	Integer bookMovie(String emailId, PaymentDTO paymentDTO, CardDTO cardDTO, ShowDTO showDTO, List<Map<String, Integer>> seats) throws MovieZoneException;
	
	List<BookingDTO> findBookingByUserEmailId(String emailId) throws MovieZoneException;
	
	BookingDTO getBookingById(Integer bookingId) throws MovieZoneException;
	
	Integer cancelBookMovie(String emailId, Integer bookingId) throws MovieZoneException;
}
