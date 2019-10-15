package com.pubkart.order.dto;

import java.sql.Date;
import java.util.List;

import com.pubkart.order.model.OrderStatus;
import com.pubkart.order.model.PaymentStatus;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderDto {
	private Long id;
	private String paymentId;
	private PaymentStatus paymentStatus;
	private OrderStatus orderStatus;
	private Date orderDate;
	private List<ItemDto> items;
	private String userId;
	private Double orderValue;
	public OrderDto() {

	}

}
