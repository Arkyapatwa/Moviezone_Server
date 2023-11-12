package com.ar.moviezone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.moviezone.dto.TheatreDTO;
import com.ar.moviezone.entity.Theatre;
import com.ar.moviezone.exception.MovieZoneException;
import com.ar.moviezone.repository.TheatreRepository;

import jakarta.transaction.Transactional;

@Service("theatreService")
@Transactional
public class TheatreServiceImpl implements TheatreService {

	@Autowired
	private TheatreService theatreService;
	
	@Autowired
	private TheatreRepository theatreRepository;
	
	@Override
	public String addTheatre(TheatreDTO theatreDTO) throws MovieZoneException {
		Theatre theatre = new Theatre();
		
		theatre.setAddress(theatreDTO.getAddress());
		theatre.setLocation(theatre.getLocation());
		theatre.setNumberOfScreens(theatre.getNumberOfScreens());
		theatre.setThreatreName(theatre.getThreatreName());
		
		theatreRepository.save(theatre);
		String response = "Theatre Added Successfully with name" + theatre.getThreatreName();
		
		return response;
	}
}
