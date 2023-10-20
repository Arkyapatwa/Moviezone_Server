package com.ar.moviezone.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import com.ar.moviezone.dto.CardDTO;
import com.ar.moviezone.dto.PaymentDTO;
import com.ar.moviezone.dto.TransactionStatus;
import com.ar.moviezone.exception.MovieZoneException;

public interface PaymentService {
	
	Map<String, String> authenticatePayment(String userEmailId, PaymentDTO paymentDTO) throws MovieZoneException,NoSuchAlgorithmException;
	Integer addPayment(PaymentDTO paymentDTO, String status) throws MovieZoneException, NoSuchAlgorithmException;
	Integer addNewCard(String userEmailId, CardDTO cardDTO) throws MovieZoneException,NoSuchAlgorithmException;
	String deleteCard(String userEmailId, CardDTO cardDTO) throws MovieZoneException;
	List<CardDTO> getAllUserCard(String userEmailId) throws MovieZoneException;
	List<CardDTO> getCardByEmailIdAndCardType(String userEmailId, String cardType) throws MovieZoneException;
}
