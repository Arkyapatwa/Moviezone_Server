package com.ar.moviezone.dto;

import java.time.LocalDate;

public class ShowDTO {

	private Integer showId;
	private LocalDate showTime;
	private Integer movieId;
	private Integer theatreId;
	private Integer screenId;
	private Integer availableTickets;
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
	public Integer getScreenId() {
		return screenId;
	}
	public void setScreenId(Integer screenId) {
		this.screenId = screenId;
	}
	
	
}
