package com.ar.moviezone.entity;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

import com.ar.moviezone.dto.BookingStatus;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;

@Entity
@Table
public class Booking {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer bookingId;
	
	private LocalDate bookingDate;
	
	private String userEmailId;
	
	private Double totalPrice;
	
	@Enumerated(EnumType.STRING)
	private BookingStatus bookingStatus;
	
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="showId")
	private Show show;
	
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

	public  BookingStatus getBookingStatus() {
		return bookingStatus;
	}

	public void setBookingStatus(BookingStatus bookingStatus) {
		this.bookingStatus = bookingStatus;
	}

	public Show getShow() {
		return show;
	}

	public void setShow(Show show) {
		this.show = show;
	}

	
	
}
