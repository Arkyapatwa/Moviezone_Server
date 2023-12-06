package com.ar.moviezone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Screen {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer screenId;
	private Integer seatingCapacity; // total seat capacity of the following screen
	private String screenName;
	private String[] seatingArrangement; // matrix of seats which will show which particular seat is available
	private Integer theatreId;
	
	public Integer getScreenId() {
		return screenId;
	}
	public void setScreenId(Integer screenId) {
		this.screenId = screenId;
	}
	public Integer getSeatingCapacity() {
		return seatingCapacity;
	}
	public void setSeatingCapacity(Integer seatingCapacity) {
		this.seatingCapacity = seatingCapacity;
	}
	public String getScreenName() {
		return screenName;
	}
	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}
	public String[] getSeatingArrangement() {
		return seatingArrangement;
	}
	public void setSeatingArrangement(String[] seatingArrangement) {
		this.seatingArrangement = seatingArrangement;
	}
	public Integer getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(Integer theatreId) {
		this.theatreId = theatreId;
	}
	
	
}
