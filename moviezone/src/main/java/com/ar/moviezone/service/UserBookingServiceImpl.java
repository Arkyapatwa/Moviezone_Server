package com.ar.moviezone.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.moviezone.dto.CardDTO;
import com.ar.moviezone.dto.MovieDTO;
import com.ar.moviezone.dto.BookingDTO;
import com.ar.moviezone.dto.BookingStatus;
import com.ar.moviezone.dto.CardDTO;
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
	private MovieRepository movieRepository;
	
	@Autowired
	private UserService userService;
	
	
	@Override
	public Integer bookMovie(String emailId, PaymentDTO paymentDTO, CardDTO cardDTO, MovieDTO movieDTO) throws MovieZoneException {
		LocalDate bookingDate = LocalDate.now();
//		Movie movie = new Movie();
//		movie.setMovieId(movieDTO.getMovieId());
//		movie.setLanguage(movieDTO.getLanguage());
//		movie.setMovieLength(movieDTO.getMovieLength());
//		movie.setMovieType(movieDTO.getMovieType());
//		movie.setName(movieDTO.getName());
		
		Optional<Movie> movieOp = movieRepository.findById(movieDTO.getMovieId());
		Movie movie = movieOp.orElseThrow(()-> new MovieZoneException(""));
		
		Booking booking = new Booking();
//		booking.setBookingStatus(paymentDTO.getTransactionStatus().equals(TransactionStatus.TRANSACTION_SUCCESS) ? BookingStatus.SUCCESSFUL : BookingStatus.FAILED);
		booking.setBookingStatus(BookingStatus.SUCCESSFUL);
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
			bookingDTO.setBookingId(booking.getBookingId());
			bookingDTO.setBookingDate(booking.getBookingDate());
			bookingDTO.setBookingStatus(booking.getBookingStatus());
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
	
	@Override
	public BookingDTO getBookingById(Integer bookingId) throws MovieZoneException {
		Optional<Booking> bookOp = bookingRepository.findById(bookingId);
		Booking booking = bookOp.orElseThrow(()-> new MovieZoneException("UserBookingService.BOOKING_NOT_FOUND"));
		
		MovieDTO movieDTO = new MovieDTO();
		movieDTO.setMovieId(booking.getMovie().getMovieId());
		movieDTO.setLanguage(booking.getMovie().getLanguage());
		movieDTO.setMovieLength(booking.getMovie().getMovieLength());
		movieDTO.setMovieType(booking.getMovie().getMovieType());
		movieDTO.setName(booking.getMovie().getName());
		
		BookingDTO bookingDTO = new BookingDTO();
		bookingDTO.setBookingDate(booking.getBookingDate());
		bookingDTO.setBookingId(bookingId);
		bookingDTO.setBookingStatus(booking.getBookingStatus());
		bookingDTO.setMovie(movieDTO);
		bookingDTO.setTotalPrice(booking.getTotalPrice());
		bookingDTO.setUserEmailId(booking.getUserEmailId());
		
		return bookingDTO;
	}
	
}
