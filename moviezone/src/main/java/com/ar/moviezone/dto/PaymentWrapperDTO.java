package com.ar.moviezone.dto;

import java.util.List;
import java.util.Map;

public class PaymentWrapperDTO {

	private PaymentDTO paymentDTO;
	private ShowDTO showDTO;
	private CardDTO cardDTO;
	private List<Map<String, Integer>> seats;
	
	
	
	public List<Map<String, Integer>> getSeats() {
		return seats;
	}
	public void setSeats(List<Map<String, Integer>> seats) {
		this.seats = seats;
	}
	public PaymentDTO getPaymentDTO() {
		return paymentDTO;
	}
	public void setPaymentDTO(PaymentDTO paymentDTO) {
		this.paymentDTO = paymentDTO;
	}

	public ShowDTO getShowDTO() {
		return showDTO;
	}
	public void setShowDTO(ShowDTO showDTO) {
		this.showDTO = showDTO;
	}
	public CardDTO getCardDTO() {
		return cardDTO;
	}
	public void setCardDTO(CardDTO cardDTO) {
		this.cardDTO = cardDTO;
	}
	
	
}
