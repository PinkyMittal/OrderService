package com.pubkart.order.feign;

import org.springframework.stereotype.Component;

import com.pubkart.order.model.Payment;
import com.pubkart.order.model.PaymentResponse;
import com.pubkart.order.model.PaymentStatus;

@Component
public class PaymentFeignServiceFallBack implements PaymentFeignService {

	@Override
	public PaymentResponse makePayment(Payment payment) {

		return new PaymentResponse(PaymentStatus.FAILURE, "");
	}

}
