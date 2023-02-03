package com.ar.moviezone.service;

import com.ar.moviezone.dto.PaymentDTO;
import com.ar.moviezone.exception.MovieZoneException;

public interface PaymentService {
	
	PaymentDTO authenticatePayment(String custEmailId, PaymentDTO paymentDTO) throws MovieZoneException;
}
