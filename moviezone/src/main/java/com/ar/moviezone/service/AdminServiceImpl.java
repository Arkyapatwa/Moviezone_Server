package com.ar.moviezone.service;

import java.util.Optional;

import org.hibernate.cfg.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.moviezone.MoviezoneApplication;
import com.ar.moviezone.dto.AdminCredentialDTO;
import com.ar.moviezone.dto.AdminDTO;
import com.ar.moviezone.entity.Admin;
import com.ar.moviezone.exception.MovieZoneException;
import com.ar.moviezone.repository.AdminRepository;

import jakarta.transaction.Transactional;

@Service("adminService")
@Transactional
public class AdminServiceImpl implements AdminService {

		@Autowired
		private AdminRepository adminRepository;
		
		@Override
		public AdminDTO adminAuthentication(AdminCredentialDTO adminCredentialDTO) throws MovieZoneException {
			AdminDTO adminDTO = null;
			Optional<Admin> adminOp = adminRepository.findById(adminCredentialDTO.getEmailId());
			Admin admin = adminOp.orElseThrow(()-> new MovieZoneException("AdminService.ADMIN_NOT_AVAILABLE"));
			
			if (!adminCredentialDTO.getPassword().equals(admin.getPassword())) {
				throw new MovieZoneException("AdminService.INVALID_CREDENTIALS");
			}
			
			adminDTO = new AdminDTO();
			adminDTO.setEmailId(adminCredentialDTO.getEmailId());
			adminDTO.setName(admin.getName());
			adminDTO.setPassword(admin.getPassword());
			adminDTO.setRole(admin.getRole());
			return adminDTO;
		}
		
		@Override
		public String registerNewAdmin(AdminDTO adminDTO) throws MovieZoneException {
			
			boolean adminEmailIdNotPresent = adminRepository.findById(adminDTO.getEmailId().toLowerCase()).isEmpty();
			
			String registeredEmailId = null;
			if (adminEmailIdNotPresent) {
				Admin admin = new Admin();
				admin.setEmailId(adminDTO.getEmailId());
				admin.setName(adminDTO.getName());
				admin.setPassword(adminDTO.getPassword());
				admin.setRole("Admin");
				
				adminRepository.save(admin);
				registeredEmailId = adminDTO.getEmailId();
			} else {
				throw new MovieZoneException("AdminService.EMAIL_ALREADY_AVAILABLE");
			}
			
			return registeredEmailId;
		}
}
