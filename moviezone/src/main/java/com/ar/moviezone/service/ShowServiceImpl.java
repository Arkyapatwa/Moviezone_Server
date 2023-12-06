package com.ar.moviezone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.moviezone.dto.ShowDTO;
import com.ar.moviezone.entity.Show;
import com.ar.moviezone.exception.MovieZoneException;
import com.ar.moviezone.repository.ShowRepository;

import jakarta.transaction.Transactional;

@Service("showService")
@Transactional
public class ShowServiceImpl implements ShowService {

//	@Autowired
//	private ShowService showService;
	
	@Autowired
	private ShowRepository showRepository;
	
	@Override
	public String addShow(ShowDTO showDTO) throws MovieZoneException {
		Show show = new Show();
		
		show.setAvailableTickets(showDTO.getAvailableTickets());
		show.setMovieId(show.getMovieId());
		show.setShowTime(showDTO.getShowTime());
		show.setTheatreId(showDTO.getTheatreId());
		show.setScreenId(showDTO.getScreenId());
		
		showRepository.save(show);
		String response = "ShowTime Added Successfully with Time" + show.getShowTime();
		
		return response;
	}
}
