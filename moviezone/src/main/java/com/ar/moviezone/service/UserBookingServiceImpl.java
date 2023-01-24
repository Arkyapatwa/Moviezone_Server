package com.ar.moviezone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.moviezone.dto.BookingDTO;
import com.ar.moviezone.dto.MovieDTO;
import com.ar.moviezone.entity.Booking;
import com.ar.moviezone.entity.User;
import com.ar.moviezone.exception.MovieZoneException;
import com.ar.moviezone.repository.BookingRepository;
import com.ar.moviezone.repository.MovieRepository;

import jakarta.transaction.Transactional;

@Service("userBookingService")
@Transactional
public class UserBookingServiceImpl implements UserBookingService{
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Override
	public Integer bookMovie(MovieDTO movieDTO) {
		return null;
	}
	
	@Override
	public List<BookingDTO> findBookingByUserEmailId(String emailId) throws MovieZoneException{
		List<Booking> bookings = bookingRepository.findByUserEmailId(emailId);
		
		if (bookings.isEmpty())
			throw new MovieZoneException("UserBookinService.BOOKING_NOT_FOUND");
		
		
		
		return null;
	}
	
}
