package com.ar.moviezone.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.moviezone.dto.ScreenDTO;
import com.ar.moviezone.entity.Screen;
import com.ar.moviezone.exception.MovieZoneException;
import com.ar.moviezone.repository.ScreenRepository;

import jakarta.transaction.Transactional;

@Service("screenService")
@Transactional
public class ScreenServiceImpl implements ScreenService {

	@Autowired
	private ScreenRepository screenRepository;
	
	@Override
	public String addScreen(ScreenDTO screenDTO) throws MovieZoneException {
		Screen screen = new Screen();
		screen.setScreenName(screenDTO.getScreenName());
		screen.setSeatingCapacity(screenDTO.getSeatingCapacity());
		screen.setTheatreId(screenDTO.getTheatreId());
		screen.setSeatingArrangement(screenDTO.getSeatingArrangement());
		
		screenRepository.save(screen);
		String response = "Screen Added Successfully with id: " + screen.getScreenId();
		return response;
	}
	
	@Override
	public List<ScreenDTO> getAllScreens(Integer theatreId) throws MovieZoneException {
		List<Screen> screenList = screenRepository.findByTheatreId(theatreId);
		if (screenList.isEmpty()) {
			throw new MovieZoneException("ScreenService.NO_SCREEN_FOUND");
		}
		
		List<ScreenDTO> screenDTOList = new ArrayList<>();
		for (Screen screen: screenList) {
			ScreenDTO screenDTO = new ScreenDTO();
			screenDTO.setScreenId(screen.getScreenId());
			screenDTO.setScreenName(screen.getScreenName());
			screenDTO.setSeatingArrangement(screen.getSeatingArrangement());
			screenDTO.setTheatreId(theatreId);
			
			screenDTOList.add(screenDTO);
		}
		
		return screenDTOList;
	}
	
	@Override
	public ScreenDTO getScreenById(Integer ScreenId) throws MovieZoneException {
		Optional<Screen> screenOp = screenRepository.findById(ScreenId);
		Screen screen = screenOp.orElseThrow(()-> new MovieZoneException("ScreenService.SCREEN_NOT_FOUND"));
		
		ScreenDTO screenDTO = new ScreenDTO();

		screenDTO.setScreenId(screen.getScreenId());
		screenDTO.setScreenName(screen.getScreenName());
		screenDTO.setSeatingArrangement(screen.getSeatingArrangement());
		screenDTO.setTheatreId(screen.getTheatreId());
		
		return screenDTO;
	}
	
	@Override
	public Boolean updateSeat(List<Map<String, Integer>> seatList, Integer ScreenId) throws MovieZoneException {
		Optional<Screen> screenOp = screenRepository.findById(ScreenId);
		Screen screen = screenOp.orElseThrow(()-> new MovieZoneException("ScreenService.SCREEN_NOT_FOUND"));
		
		String[] seatingArrangement = screen.getSeatingArrangement();
		
		for (Map<String, Integer> seat: seatList) {
			Integer row = seat.get("rowIndex");
			Integer col = seat.get("colIndex"); 
			Integer rowLength  = seatingArrangement[row].length();
			seatingArrangement[row] = seatingArrangement[row].substring(0, col) + "1" + seatingArrangement[row].substring(col+1, rowLength);
		}
		
		screen.setSeatingArrangement(seatingArrangement);
		screenRepository.save(screen);
		
		return true;
	}
	
	@Override
	public Boolean cancelSeat(String[] seatList, Integer ScreenId) throws MovieZoneException {
		Optional<Screen> screenOp = screenRepository.findById(ScreenId);
		Screen screen = screenOp.orElseThrow(()-> new MovieZoneException("ScreenService.SCREEN_NOT_FOUND"));
		
		String[] seatingArrangement = screen.getSeatingArrangement();
		String val = "";
		Integer row = 0;
		Integer col = 0;
		for (String seat: seatList) {
			for (int i = 0 ; i < seat.length() ; i++) {
				if (seat.charAt(i) !='R' && seat.charAt(i) !='C') {
					val += seat.charAt(i);
				} else {
					if (seat.charAt(i) =='R') {
						row = Integer.parseInt(val);
					} else {
						col = Integer.parseInt(val);
					}
					val = "";
				}
			}
			Integer rowLength  = seatingArrangement[row].length();
			seatingArrangement[row] = seatingArrangement[row].substring(0, col) + "0" + seatingArrangement[row].substring(col+1, rowLength);
		}
		
		screen.setSeatingArrangement(seatingArrangement);
		screenRepository.save(screen);
		
		return true;
	}
}
