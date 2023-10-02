package com.ar.moviezone.service;

import java.util.List;

import com.ar.moviezone.dto.CardDTO;
import com.ar.moviezone.dto.PaymentDTO;
import com.ar.moviezone.exception.MovieZoneException;

public interface PaymentService {
	
	PaymentDTO authenticatePayment(String userEmailId, PaymentDTO paymentDTO) throws MovieZoneException;
	CardDTO addNewCard(CardDTO cardDTO) throws MovieZoneException;
	String deleteCard(CardDTO cardDTO) throws MovieZoneException;
	List<CardDTO> getAllCustomerCard(String emailId) throws MovieZoneException;
	List<CardDTO> getCardByEmailIdAndCardType(String emailId, String cardType) throws MovieZoneException;
}
