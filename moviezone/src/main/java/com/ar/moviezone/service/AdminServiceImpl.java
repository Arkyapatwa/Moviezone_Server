package com.ar.moviezone.service;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.moviezone.dto.AdminDTO;
import com.ar.moviezone.exception.MovieZoneException;
import com.ar.moviezone.repository.AdminRepository;

import jakarta.transaction.Transactional;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

		@Autowired
		private AdminRepository adminRepository;
		
		@Override
		public AdminDTO adminAuthentication(String emailId, String password) throws MovieZoneException {
			return null;
		}
		
		@Override
		public String registerNewAdmin(AdminDTO adminDTO) throws MovieZoneException {
			return null;
		}
}
