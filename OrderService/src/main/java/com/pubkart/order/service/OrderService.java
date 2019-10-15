package com.pubkart.order.service;

import java.util.List;

import com.pubkart.order.dto.OrderDto;
import com.pubkart.order.dto.UserDto;

public interface OrderService {

	public OrderDto createOrder(UserDto user);

	public List<OrderDto> getOrders(String userId);

}
