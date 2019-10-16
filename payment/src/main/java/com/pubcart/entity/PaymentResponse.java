package com.pubcart.entity;

public class PaymentResponse {

	private Status status;
	private String transactionId;
	
	public PaymentResponse() {
	}

	public PaymentResponse(Status status, String transactionId) {
		super();
		this.status = status;
		this.transactionId = transactionId;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public String getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(String transactionId) {
		this.transactionId = transactionId;
	}
	
	
}
