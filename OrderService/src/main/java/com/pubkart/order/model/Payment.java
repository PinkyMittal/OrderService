package com.pubkart.order.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class Payment {

	private String userId;
	private String orderId;
	private Double amount;
	private CardDetails cardDetails;
	
	public Payment() {
	}

	
} 
 

