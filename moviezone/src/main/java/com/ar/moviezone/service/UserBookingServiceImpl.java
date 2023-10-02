package com.ar.moviezone.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.moviezone.dto.BookingDTO;
import com.ar.moviezone.dto.BookingStatus;
import com.ar.moviezone.dto.MovieDTO;
import com.ar.moviezone.dto.PaymentDTO;
import com.ar.moviezone.dto.TransactionStatus;
import com.ar.moviezone.entity.Booking;
import com.ar.moviezone.entity.Movie;
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
	
	@Autowired
	private UserService userService;
	
	
	@Override
	public Integer bookMovie(String emailId, PaymentDTO paymentDTO) throws MovieZoneException {
		LocalDate bookingDate = LocalDate.now();
		Movie movie = new Movie();
		movie.setMovieId(paymentDTO.getMovieDTO().getMovieId());
		movie.setLanguage(paymentDTO.getMovieDTO().getLanguage());
		movie.setMovieLength(paymentDTO.getMovieDTO().getMovieLength());
		movie.setMovieType(paymentDTO.getMovieDTO().getMovieType());
		movie.setName(paymentDTO.getMovieDTO().getName());
		
		Booking booking = new Booking();
		booking.setBookingId(null);
		booking.setBookingStatus(paymentDTO.getTransactionStatus().equals(TransactionStatus.TRANSACTION_SUCCESS) ? BookingStatus.SUCCESSFUL : BookingStatus.FAILED);
		booking.setBookingDate(bookingDate);
		booking.setMovie(movie);
		booking.setTotalPrice(paymentDTO.getTotalPrice());
		booking.setUserEmailId(emailId);
		
		bookingRepository.save(booking);
		return booking.getBookingId();
	}
	
	@Override
	public List<BookingDTO> findBookingByUserEmailId(String emailId) throws MovieZoneException {
		List<Booking> bookings = bookingRepository.findByUserEmailId(emailId);
		
		if (bookings.isEmpty())
			throw new MovieZoneException("UserBookingService.BOOKING_NOT_FOUND");
		
		List<BookingDTO> bookingDTOs = new ArrayList<>();
		
		for (Booking booking : bookings) {
			
			BookingDTO bookingDTO = new BookingDTO();
			bookingDTO.setBookingDate(booking.getBookingDate());
			bookingDTO.setBookingStatus(booking.getBookingStatus().toString());
			bookingDTO.setTotalPrice(booking.getTotalPrice());
			bookingDTO.setUserEmailId(booking.getUserEmailId());
			
			MovieDTO movieDTO = new MovieDTO();
			movieDTO.setLanguage(booking.getMovie().getLanguage());
			movieDTO.setMovieId(booking.getMovie().getMovieId());
			movieDTO.setMovieLength(booking.getMovie().getMovieLength());
			movieDTO.setMovieType(booking.getMovie().getMovieType());
			movieDTO.setName(booking.getMovie().getName());
			
			bookingDTO.setMovie(movieDTO);
			
			bookingDTOs.add(bookingDTO);
		}
		
		return bookingDTOs;
	}
	
}
