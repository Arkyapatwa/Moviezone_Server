package com.ar.moviezone.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.moviezone.dto.PaymentDTO;
import com.ar.moviezone.exception.MovieZoneException;
import com.ar.moviezone.repository.PaymentRepository;

import jakarta.transaction.Transactional;

@Service("paymentService")
@Transactional
public class PaymentServiceImpl implements PaymentService{
		
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Override
	public PaymentDTO authenticatePayment(String custEmailId, PaymentDTO paymentDTO) throws MovieZoneException{
		return null;
	}
}
