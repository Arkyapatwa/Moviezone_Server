package com.ar.moviezone.service;

import com.ar.moviezone.dto.AdminDTO;
import com.ar.moviezone.exception.MovieZoneException;

public interface AdminService {

	AdminDTO adminAuthentication(String emailId, String password) throws MovieZoneException;
	String registerNewAdmin(AdminDTO adminDTO) throws MovieZoneException;
}
