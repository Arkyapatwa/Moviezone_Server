package com.ar.moviezone.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import com.ar.moviezone.dto.CardDTO;
import com.ar.moviezone.dto.PaymentDTO;
import com.ar.moviezone.exception.MovieZoneException;

public interface PaymentService {
	
	PaymentDTO authenticatePayment(String userEmailId, PaymentDTO paymentDTO) throws MovieZoneException;
	Integer addNewCard(String userEmailId, CardDTO cardDTO) throws MovieZoneException,NoSuchAlgorithmException;
	String deleteCard(String userEmailId, CardDTO cardDTO) throws MovieZoneException;
	List<CardDTO> getAllUserCard(String userEmailId) throws MovieZoneException;
	List<CardDTO> getCardByEmailIdAndCardType(String userEmailId, String cardType) throws MovieZoneException;
}
