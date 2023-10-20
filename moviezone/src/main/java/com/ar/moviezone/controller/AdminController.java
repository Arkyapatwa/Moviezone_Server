package com.ar.moviezone.controller;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ar.moviezone.dto.AdminCredentialDTO;
import com.ar.moviezone.dto.AdminDTO;
import com.ar.moviezone.exception.MovieZoneException;
import com.ar.moviezone.service.AdminService;

@RestController
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	
	@Autowired
	private Environment environment;
	
	@PostMapping("/register")
	public ResponseEntity<String> registerNewAdmin(@RequestBody AdminDTO adminDTO) throws MovieZoneException {
		String message = adminService.registerNewAdmin(adminDTO);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@PostMapping("/authenticate")
	public ResponseEntity<AdminDTO> authenticateAdmin(@RequestBody AdminCredentialDTO adminCredDTO) throws MovieZoneException {
		AdminDTO adminDTO = adminService.adminAuthentication(adminCredDTO);
		return new ResponseEntity<>(adminDTO, HttpStatus.OK);
	}
}
