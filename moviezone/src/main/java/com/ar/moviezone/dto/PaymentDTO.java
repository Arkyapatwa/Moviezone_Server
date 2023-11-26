package com.ar.moviezone.dto;

import java.time.LocalDate;

public class PaymentDTO {
	
	private Integer paymentId;
	private TransactionStatus transactionStatus;
	private Double totalPrice;
	private LocalDate paymentDate;
	private Integer showId;
	private Integer cardId;
	
	
	public Integer getShowId() {
		return showId;
	}
	public void setShowId(Integer showId) {
		this.showId = showId;
	}
	public Integer getCardId() {
		return cardId;
	}
	public void setCardId(Integer cardId) {
		this.cardId = cardId;
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
