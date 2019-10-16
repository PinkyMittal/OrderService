package com.pubcart.service.impl;

import org.apache.commons.lang.RandomStringUtils;
import org.springframework.stereotype.Service;

import com.pubcart.entity.Payment;
import com.pubcart.entity.PaymentResponse;
import com.pubcart.entity.Status;
import com.pubcart.service.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

	@Override
	public PaymentResponse generatePayment(Payment payment) {
		System.out.println("OrderId : "+payment.getOrderId());
		if (payment.getOrderId().length()%2==0) {
			return new PaymentResponse(Status.FAILURE, "FLR"+RandomStringUtils.randomNumeric(6));
		} else {
			return new PaymentResponse(Status.SUCCESS,"PYT"+RandomStringUtils.randomNumeric(6));
		}
	}

}
