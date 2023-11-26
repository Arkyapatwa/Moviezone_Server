package com.ar.moviezone.dto;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

public class BookingDTO {
	
	private Integer bookingId;
	private LocalDate bookingDate;
	private String userEmailId;
	private Double totalPrice;
	private BookingStatus bookingStatus;
	private ShowDTO showDTO;
	private List<Map<String, Integer>> seats;

	
	public List<Map<String, Integer>> getSeats() {
		return seats;
	}
	public void setSeats(List<Map<String, Integer>> seats) {
		this.seats = seats;
	}
	public Integer getBookingId() {
		return bookingId;
	}
	public void setBookingId(Integer bookingId) {
		this.bookingId = bookingId;
	}
	public LocalDate getBookingDate() {
		return bookingDate;
	}
	public void setBookingDate(LocalDate bookingDate) {
		this.bookingDate = bookingDate;
	}
	public String getUserEmailId() {
		return userEmailId;
	}
	public void setUserEmailId(String userEmailId) {
		this.userEmailId = userEmailId;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	
	public BookingStatus getBookingStatus() {
		return bookingStatus;
	}
	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}
	public ShowDTO getShowDTO() {
		return showDTO;
	}
	public void setShowDTO(ShowDTO showDTO) {
		this.showDTO = showDTO;
	}

		
	
}
