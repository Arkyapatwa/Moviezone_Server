package com.ar.moviezone.service;

import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ar.moviezone.dto.CardDTO;
import com.ar.moviezone.dto.MovieDTO;
import com.ar.moviezone.dto.PaymentDTO;
import com.ar.moviezone.dto.TransactionStatus;
import com.ar.moviezone.entity.Card;
import com.ar.moviezone.entity.Movie;
import com.ar.moviezone.entity.Payment;
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
	
	@Autowired
	private UserBookingService userBookingService;
	
	@Override
	public Map<String,String> authenticatePayment(String userEmailId, PaymentDTO paymentDTO) throws MovieZoneException, NoSuchAlgorithmException{
		Optional<Card> cardOp = cardRepository.findById(paymentDTO.getCardDTO().getCardId());
		Card card = cardOp.orElseThrow(()-> new MovieZoneException("PaymentService.CARD_NOT_FOUND"));
		
		String paymentResponse = null;
		Integer BookingId = null;
		Integer paymentId = null;
		if (card.getCvv().equals(HashingUtility.hashValuesSHA256(paymentDTO.getCardDTO().getCvv()))) {
			paymentResponse = "TRANSACTION_SUCCESS";
			paymentId = addPayment(paymentDTO, paymentResponse);
			BookingId = userBookingService.bookMovie(userEmailId, paymentDTO);
		} else {
			paymentResponse = "TRANSACTION_FAILED";
			paymentId = addPayment(paymentDTO, paymentResponse);
		}
		Map<String, String> response = new HashMap<>();
		response.put("TransactionStatus", paymentResponse);
		response.put("BookingId", Integer.toString(BookingId));
		response.put("PaymentId", Integer.toString(paymentId));
		return response;
	}
	
	@Override
	public Integer addPayment(PaymentDTO paymentDTO, String status) throws MovieZoneException, NoSuchAlgorithmException {
		Card Card = new Card();
		Card.setCardId(paymentDTO.getCardDTO().getCardId());
		Card.setCardNumber(paymentDTO.getCardDTO().getCardNumber());
		Card.setCardType(paymentDTO.getCardDTO().getCardType());
		Card.setExpiryDate(paymentDTO.getCardDTO().getExpiryDate());
		Card.setNameOnCard(paymentDTO.getCardDTO().getNameOnCard());
		Card.setUserEmailId(paymentDTO.getCardDTO().getUserEmailId());
		Card.setCvv(HashingUtility.hashValuesSHA256(paymentDTO.getCardDTO().getCvv()));
		
		Movie movie = new Movie();
		movie.setLanguage(paymentDTO.getMovieDTO().getLanguage());
		movie.setMovieId(paymentDTO.getMovieDTO().getMovieId());
		movie.setMovieLength(paymentDTO.getMovieDTO().getMovieLength());
		movie.setMovieType(paymentDTO.getMovieDTO().getMovieType());
		movie.setName(paymentDTO.getMovieDTO().getName());
		
		Payment payment = new Payment();
		payment.setCard(Card);
		payment.setMovie(movie);
		payment.setPaymentDate(LocalDate.now());
		payment.setPaymentId(paymentDTO.getPaymentId());
		payment.setTotalPrice(paymentDTO.getTotalPrice());
		if (status.equals("TRANSACTION_SUCCESS")) {
			payment.setTransactionStatus(TransactionStatus.TRANSACTION_SUCCESS);
		} else {
			payment.setTransactionStatus(TransactionStatus.TRANSACTION_FAILED);
		}
		
		paymentRepository.save(payment);
		
		return payment.getPaymentId();
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
