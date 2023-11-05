package com.ar.moviezone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Seat {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer seatId;
	private Integer rowNumber;
	private Integer seatNumber; //seat number within the row
	private boolean availablity; //If seats is available or not
	public Integer getSeatId() {
		return seatId;
	}
	public void setSeatId(Integer seatId) {
		this.seatId = seatId;
	}
	public Integer getRowNumber() {
		return rowNumber;
	}
	public void setRowNumber(Integer rowNumber) {
		this.rowNumber = rowNumber;
	}
	public Integer getSeatNumber() {
		return seatNumber;
	}
	public void setSeatNumber(Integer seatNumber) {
		this.seatNumber = seatNumber;
	}
	public boolean isAvailablity() {
		return availablity;
	}
	public void setAvailablity(boolean availablity) {
		this.availablity = availablity;
	}
	
	
}
