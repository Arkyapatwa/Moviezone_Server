package com.ar.moviezone.dto;

import java.time.LocalDate;

public class PaymentDTO {
	
	private Integer paymentId;
	private TransactionStatus transactionStatus;
	private Double totalPrice;
	private LocalDate paymentDate;
	private MovieDTO movieDTO;
	
	public MovieDTO getMovieDTO() {
		return movieDTO;
	}
	public void setMovieDTO(MovieDTO movieDTO) {
		this.movieDTO = movieDTO;
	}
	public Integer getPaymentId() {
		return paymentId;
	}
	public void setPaymentId(Integer paymentId) {
		this.paymentId = paymentId;
	}
	public TransactionStatus getTransactionStatus() {
		return transactionStatus;
	}
	public void setTransactionStatus(TransactionStatus transactionStatus) {
		this.transactionStatus = transactionStatus;
	}
	public Double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(Double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public LocalDate getPaymentDate() {
		return paymentDate;
	}
	public void setPaymentDate(LocalDate paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	
}
