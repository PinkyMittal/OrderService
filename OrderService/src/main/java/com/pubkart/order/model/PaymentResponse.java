package com.pubkart.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class PaymentResponse {
	private PaymentStatus status;
	private String transactionId;
	
	public PaymentResponse() {
		
	} 
}
