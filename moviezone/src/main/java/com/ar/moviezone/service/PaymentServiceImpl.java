package com.ar.moviezone.service;

import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
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
	public String deleteCard(String userEmailId, CardDTO cardDTO) throws MovieZoneException {
		List<Card> cards = cardRepository.findByUserEmailId(userEmailId);
		
		if (cards.isEmpty()) {
			throw new MovieZoneException("PaymentService.NO_CARD_AVAILABLE");
		}
		Optional<Card> cardOp = cardRepository.findById(cardDTO.getCardId());
		Card card = cardOp.orElseThrow(()-> new MovieZoneException("PaymentService.CARD_NOT_FOUND"));
		
		String response = "Card Deleted Successfully ending with" + card.getCardNumber().substring(12,16);
		cardRepository.delete(card);
		return response;
	}
	@Override
	public List<CardDTO> getAllUserCard(String userEmailId) throws MovieZoneException {
		List<Card> cards = cardRepository.findByUserEmailId(userEmailId);
		
		if (cards.isEmpty()) {
			throw new MovieZoneException("PaymentService.NO_CARD_AVAILABLE");
		}
		List<CardDTO> cardDTOs = new ArrayList<>();
		for (Card card: cards) {
			CardDTO cardDTO = new CardDTO();
			cardDTO.setCardId(card.getCardId());
			cardDTO.setCardNumber(card.getCardNumber());
			cardDTO.setCardType(card.getCardType());
			cardDTO.setExpiryDate(card.getExpiryDate());
			cardDTO.setNameOnCard(card.getNameOnCard());
			cardDTO.setUserEmailId(card.getUserEmailId());
			
			cardDTOs.add(cardDTO);
		}
		return cardDTOs;
		
	}
	@Override
	public List<CardDTO> getCardByEmailIdAndCardType(String userEmailId, String cardType) throws MovieZoneException {
		List<Card> cards = cardRepository.findByUserEmailId(userEmailId);
		
		if (cards.isEmpty()) {
			throw new MovieZoneException("PaymentService.NO_CARD_AVAILABLE");
		}
		List<CardDTO> cardDTOs = new ArrayList<>();
		for (Card card: cards) {
			if (card.getCardType().equals(cardType)) {
				CardDTO cardDTO = new CardDTO();
				cardDTO.setCardId(card.getCardId());
				cardDTO.setCardNumber(card.getCardNumber());
				cardDTO.setCardType(card.getCardType());
				cardDTO.setExpiryDate(card.getExpiryDate());
				cardDTO.setNameOnCard(card.getNameOnCard());
				cardDTO.setUserEmailId(card.getUserEmailId());
				
				cardDTOs.add(cardDTO);
			}
		}
		return cardDTOs;
	}
}
