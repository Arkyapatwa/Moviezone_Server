package com.ar.moviezone.service;

import java.util.List;
import java.util.Map;

import com.ar.moviezone.dto.ScreenDTO;
import com.ar.moviezone.exception.MovieZoneException;

public interface ScreenService {

	String addScreen(ScreenDTO screenDTO) throws MovieZoneException;
	List<ScreenDTO> getAllScreens(Integer theatreId) throws MovieZoneException;
	ScreenDTO getScreenById(Integer ScreenId) throws MovieZoneException;
	Boolean updateSeat(List<Map<String, Integer>> seatList, Integer ScreenId) throws MovieZoneException;
	Boolean cancelSeat(String[] seatList, Integer ScreenId) throws MovieZoneException;
}
