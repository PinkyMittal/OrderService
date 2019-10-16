package com.pubcart.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pubcart.entity.Payment;
import com.pubcart.entity.PaymentResponse;
import com.pubcart.service.PaymentService;

@RestController
public class PaymentController {
	
	
	@Autowired
	private PaymentService paymentService;
	
	
	@PostMapping("/makePayment")
	public PaymentResponse generatePayment(@RequestBody Payment payment) {
		return paymentService.generatePayment(payment);
	}

}
