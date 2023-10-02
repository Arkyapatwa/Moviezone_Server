package com.ar.moviezone.service;

import java.util.List;

import com.ar.moviezone.dto.BookingDTO;
import com.ar.moviezone.dto.MovieDTO;
import com.ar.moviezone.exception.MovieZoneException;

public interface UserBookingService {
	
	Integer bookMovie(String emailId, MovieDTO movieDTO) throws MovieZoneException;
	
	List<BookingDTO> findBookingByUserEmailId(String emailId) throws MovieZoneException;
}
