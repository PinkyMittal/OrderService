package com.pubcart.service;

import com.pubcart.entity.Payment;
import com.pubcart.entity.PaymentResponse;

public interface PaymentService {
	
	PaymentResponse generatePayment(Payment payment);

}
