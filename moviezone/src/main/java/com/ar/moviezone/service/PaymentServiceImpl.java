package com.ar.moviezone.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.moviezone.dto.CardDTO;
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
	public PaymentDTO authenticatePayment(String userEmailId, PaymentDTO paymentDTO) throws MovieZoneException{
		return null;
	}
	
	@Override
	public CardDTO addNewCard(CardDTO cardDTO) throws MovieZoneException {
		return null;
	}
	@Override
	public String deleteCard(CardDTO cardDTO) throws MovieZoneException {
		return null;
	}
	@Override
	public List<CardDTO> getAllCustomerCard(String emailId) throws MovieZoneException {
		return null;
	}
	@Override
	public List<CardDTO> getCardByEmailIdAndCardType(String emailId, String cardType) throws MovieZoneException {
		return null;
	}
}
