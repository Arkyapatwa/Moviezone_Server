package com.ar.moviezone.dto;

public class MovieDTO {
	
	private Integer movieId;
	private String name;
	private String movieLength;
	private String language;
	private MovieType movieType;
	
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
	public MovieType getMovieType() {
		return movieType;
	}
	public void setMovieType(MovieType movieType2) {
		this.movieType = movieType2;
	}
	
	
	
}
