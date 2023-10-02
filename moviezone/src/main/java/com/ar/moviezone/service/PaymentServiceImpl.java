package com.ar.moviezone.service;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.moviezone.dto.CardDTO;
import com.ar.moviezone.dto.PaymentDTO;
import com.ar.moviezone.entity.Card;
import com.ar.moviezone.exception.MovieZoneException;
import com.ar.moviezone.repository.CardRepository;
import com.ar.moviezone.repository.PaymentRepository;
import com.ar.moviezone.utility.HashingUtility;

import jakarta.transaction.Transactional;

@Service("paymentService")
@Transactional
public class PaymentServiceImpl implements PaymentService{
		
	@Autowired
	private PaymentRepository paymentRepository;
	
	@Autowired
	private CardRepository cardRepository;
	
	@Override
	public PaymentDTO authenticatePayment(String userEmailId, PaymentDTO paymentDTO) throws MovieZoneException{
		return null;
	}
	
	@Override
	public Integer addNewCard(String userEmailId, CardDTO cardDTO) throws MovieZoneException, NoSuchAlgorithmException {
		Card availableCard = cardRepository.findByUserEmailIdAndCardNumber(userEmailId, cardDTO.getCardNumber());
		
		if (availableCard.getCardNumber().equals(cardDTO.getCardNumber())) {
			throw new MovieZoneException("PaymentService.CARD_ALREADY_AVAILABLE");
		}
		
		String hashedValueCvv = HashingUtility.hashValuesSHA256(cardDTO.getCvv());
		
		Card newCard = new Card();
		newCard.setCardId(cardDTO.getCardId());
		newCard.setCardNumber(cardDTO.getCardNumber());
		newCard.setCardType(cardDTO.getCardType());
		newCard.setExpiryDate(cardDTO.getExpiryDate());
		newCard.setNameOnCard(cardDTO.getNameOnCard());
		newCard.setUserEmailId(userEmailId);
		newCard.setCvv(hashedValueCvv);
		
		cardRepository.save(newCard);
		return newCard.getCardId();
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
