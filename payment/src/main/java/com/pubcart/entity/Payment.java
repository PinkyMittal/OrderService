package com.pubcart.entity;

public class Payment {

	private String userId;
	private String orderId;
	private Double amount;
	private CardDetails cardDetails;
	
	public Payment() {
	}

	public Payment(String id, String orderId, Double amount, CardDetails cardDetails) {
		super();
		this.userId = id;
		this.orderId = orderId;
		this.amount = amount;
		this.cardDetails = cardDetails;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String id) {
		this.userId = id;
	}

	public String getOrderId() {
		return orderId;
	}

	public void setOrderId(String orderId) {
		this.orderId = orderId;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public CardDetails getCardDetails() {
		return cardDetails;
	}

	public void setCardDetails(CardDetails cardDetails) {
		this.cardDetails = cardDetails;
	}
	
}
