package com.ar.moviezone.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table
public class Theatre {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer theatreId;
	private String threatreName;
	private String address;
	private String location; //city,state 
	private Integer numberOfScreens;
	public Integer getTheatreId() {
		return theatreId;
	}
	public void setTheatreId(Integer theatreId) {
		this.theatreId = theatreId;
	}
	public String getThreatreName() {
		return threatreName;
	}
	public void setThreatreName(String threatreName) {
		this.threatreName = threatreName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getNumberOfScreens() {
		return numberOfScreens;
	}
	public void setNumberOfScreens(Integer numberOfScreens) {
		this.numberOfScreens = numberOfScreens;
	}
	
	
}
