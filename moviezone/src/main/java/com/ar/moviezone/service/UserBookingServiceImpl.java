package com.ar.moviezone.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
import com.ar.moviezone.dto.ShowDTO;
import com.ar.moviezone.dto.TransactionStatus;
import com.ar.moviezone.entity.Booking;
import com.ar.moviezone.entity.Movie;
import com.ar.moviezone.entity.Show;
import com.ar.moviezone.entity.User;
import com.ar.moviezone.exception.MovieZoneException;
import com.ar.moviezone.repository.BookingRepository;
import com.ar.moviezone.repository.MovieRepository;
import com.ar.moviezone.repository.ShowRepository;

import jakarta.transaction.Transactional;

@Service("userBookingService")
@Transactional
public class UserBookingServiceImpl implements UserBookingService{
	
	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private ShowRepository showRepository;
	
	@Autowired
	private ScreenService screenService;
	
	
	@Override
	public Integer bookMovie(String emailId, PaymentDTO paymentDTO, CardDTO cardDTO, ShowDTO showDTO, List<Map<String, Integer>> seats) throws MovieZoneException {
		LocalDate bookingDate = LocalDate.now();
		
		Optional<Show> showOp = showRepository.findById(showDTO.getShowId());
		Show show = showOp.orElseThrow(()-> new MovieZoneException(""));
		
		Booking booking = new Booking();
		booking.setBookingStatus(BookingStatus.SUCCESSFUL);
		booking.setBookingDate(bookingDate);
		booking.setShow(show);
		booking.setTotalPrice(paymentDTO.getTotalPrice());
		booking.setUserEmailId(emailId);
		
		Integer screenId = showDTO.getScreenId();
		
		Boolean seatBookedSuccessfully = screenService.updateSeat(seats, screenId);
		booking.setSeats(seats);
		
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
			bookingDTO.setSeats(booking.getSeats());
			
			ShowDTO showDTO = new ShowDTO();
			showDTO.setMovieId(booking.getShow().getMovieId());
			showDTO.setShowId(booking.getShow().getShowId());
			showDTO.setShowTime(booking.getShow().getShowTime());
			showDTO.setTheatreId(booking.getShow().getTheatreId());
			showDTO.setScreenId(booking.getShow().getScreenId());
			
			bookingDTO.setShowDTO(showDTO);
			
			bookingDTOs.add(bookingDTO);
		}
		
		return bookingDTOs;
	}
	
	@Override
	public BookingDTO getBookingById(Integer bookingId) throws MovieZoneException {
		Optional<Booking> bookOp = bookingRepository.findById(bookingId);
		Booking booking = bookOp.orElseThrow(()-> new MovieZoneException("UserBookingService.BOOKING_NOT_FOUND"));
		
		ShowDTO showDTO = new ShowDTO();
		showDTO.setMovieId(booking.getShow().getMovieId());
		showDTO.setShowId(booking.getShow().getShowId());
		showDTO.setShowTime(booking.getShow().getShowTime());
		showDTO.setTheatreId(booking.getShow().getTheatreId());
		showDTO.setScreenId(booking.getShow().getScreenId());
		
		BookingDTO bookingDTO = new BookingDTO();
		bookingDTO.setBookingDate(booking.getBookingDate());
		bookingDTO.setBookingId(bookingId);
		bookingDTO.setBookingStatus(booking.getBookingStatus());
		bookingDTO.setShowDTO(showDTO);;
		bookingDTO.setTotalPrice(booking.getTotalPrice());
		bookingDTO.setUserEmailId(booking.getUserEmailId());
		bookingDTO.setSeats(booking.getSeats());
		
		return bookingDTO;
	}
	
	@Override
	public Integer cancelBookMovie(String emailId, Integer bookingId) throws MovieZoneException {
		Optional<Booking> bookOp = bookingRepository.findById(bookingId);
		Booking booking = bookOp.orElseThrow(()-> new MovieZoneException("UserBookingService.BOOKING_NOT_FOUND"));
		
		Integer screenId = booking.getShow().getScreenId();
		List<Map<String, Integer>> seats = booking.getSeats();
		
		Boolean seatBookedSuccessfully = screenService.cancelSeat(seats, screenId);
		booking.setBookingStatus(BookingStatus.CANCELLED);
		
		bookingRepository.save(booking);
		return bookingId;
	}
	
}
