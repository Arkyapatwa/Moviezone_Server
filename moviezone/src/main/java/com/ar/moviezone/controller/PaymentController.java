package com.ar.moviezone.controller;

import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.Map;

import org.springframework.core.env.Environment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ar.moviezone.dto.CardDTO;
import com.ar.moviezone.dto.PaymentDTO;
import com.ar.moviezone.dto.PaymentWrapperDTO;
import com.ar.moviezone.exception.MovieZoneException;
import com.ar.moviezone.service.PaymentService;

@RestController
@RequestMapping("/payment")
public class PaymentController {

	@Autowired
	private PaymentService payService;
	
	@Autowired
	private Environment environment;
	
	@PostMapping("/addCard/{emailId}")
	public ResponseEntity<String> addNewCard(@PathVariable("emailId") String emailId, @RequestBody CardDTO cardDTO) throws MovieZoneException, NoSuchAlgorithmException {
		String response = payService.addNewCard(emailId, cardDTO);
		return new ResponseEntity<>(response, HttpStatus.OK); // proper message required
	}
	
	@DeleteMapping("/deleteCard/{emailId}")
	public ResponseEntity<String> deleteCard(@PathVariable("emailId") String emailId, @RequestBody CardDTO cardDTO) throws MovieZoneException {
		String message = payService.deleteCard(emailId, cardDTO);
		return new ResponseEntity<>(message, HttpStatus.OK);
	}
	
	@GetMapping("/getAllCards/{emailId}")
	public ResponseEntity<List<CardDTO>> getAllUserCards(@PathVariable("emailId") String emailId) throws MovieZoneException {
		List<CardDTO> cardDTOs = payService.getAllUserCard(emailId);
		return new ResponseEntity<>(cardDTOs, HttpStatus.OK);
	}
	
	@GetMapping("/getCardsBYType/{emailId}/{type}")
	public ResponseEntity<List<CardDTO>> getCardsbyIdAndType(@PathVariable("emailId") String emailId, @PathVariable("type") String cardType) throws MovieZoneException {
		List<CardDTO> cardDTOs = payService.getCardByEmailIdAndCardType(emailId, cardType);
		return new ResponseEntity<>(cardDTOs, HttpStatus.OK);
	}
	
	@PostMapping("/authenticate/{emailId}")
	public ResponseEntity<Map<String, String>> authenticatePayment(@PathVariable("emailId") String emailId, @RequestBody PaymentWrapperDTO paymentWDTO) throws MovieZoneException, NoSuchAlgorithmException {
		Map<String, String> response = payService.authenticatePayment(emailId, paymentWDTO.getPaymentDTO(), paymentWDTO.getCardDTO(), paymentWDTO.getMovieDTO());
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
