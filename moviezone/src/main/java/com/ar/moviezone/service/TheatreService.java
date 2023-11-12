package com.ar.moviezone.service;

import com.ar.moviezone.dto.TheatreDTO;
import com.ar.moviezone.exception.MovieZoneException;

public interface TheatreService {

	String addTheatre(TheatreDTO theatreDTO) throws MovieZoneException;
}
