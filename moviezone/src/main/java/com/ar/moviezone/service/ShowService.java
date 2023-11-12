package com.ar.moviezone.service;

import com.ar.moviezone.dto.ShowDTO;
import com.ar.moviezone.exception.MovieZoneException;

public interface ShowService {

	String addShow(ShowDTO showDTO) throws MovieZoneException;
}
