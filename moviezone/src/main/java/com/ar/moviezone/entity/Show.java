package com.ar.moviezone.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Show {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer showId;
	private LocalDate showTime;
	private Integer movieId;
	private Integer theatreId;
	private Integer availableTickets; //Number of available tickets for the show
	public Integer getShowId() {
		return showId;
	}
	public void setShowId(Integer showId) {
		this.showId = showId;
	}
	public LocalDate getShowTime() {
		return showTime;
	}
	public void setShowTime(LocalDate showTime) {
		this.showTime = showTime;
	}
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public Integer getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(Integer theatreId) {
		this.theatreId = theatreId;
	}
	public Integer getAvailableTickets() {
		return availableTickets;
	}
	public void setAvailableTickets(Integer availableTickets) {
		this.availableTickets = availableTickets;
	}
	
	
}
