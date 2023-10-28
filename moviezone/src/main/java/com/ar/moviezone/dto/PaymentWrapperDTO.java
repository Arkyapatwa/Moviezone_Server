package com.ar.moviezone.dto;

public class PaymentWrapperDTO {

	private PaymentDTO paymentDTO;
	private MovieDTO movieDTO;
	private CardDTO cardDTO;
	public PaymentDTO getPaymentDTO() {
		return paymentDTO;
	}
	public void setPaymentDTO(PaymentDTO paymentDTO) {
		this.paymentDTO = paymentDTO;
	}
	public MovieDTO getMovieDTO() {
		return movieDTO;
	}
	public void setMovieDTO(MovieDTO movieDTO) {
		this.movieDTO = movieDTO;
	}
	public CardDTO getCardDTO() {
		return cardDTO;
	}
	public void setCardDTO(CardDTO cardDTO) {
		this.cardDTO = cardDTO;
	}
	
	
}
