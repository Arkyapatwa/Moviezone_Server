package com.ar.moviezone.dto;

public class MovieDTO {
	
	private Integer movieId;
	private String name;
	private String movieLength;
	private String language;
	private String type;
	
	public Integer getMovieId() {
		return movieId;
	}
	public void setMovieId(Integer movieId) {
		this.movieId = movieId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMovieLength() {
		return movieLength;
	}
	public void setMovieLength(String movieLength) {
		this.movieLength = movieLength;
	}
	public String getLanguage() {
		return language;
	}
	public void setLanguage(String language) {
		this.language = language;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	
	
}
