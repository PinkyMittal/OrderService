package com.pubkart.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pubkart.order.dto.OrderDto;
import com.pubkart.order.dto.UserDto;
import com.pubkart.order.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	OrderService orderService;

	@PostMapping("/order")
	public OrderDto createOrder(@RequestBody UserDto user) {

		return orderService.createOrder(user);
	}

	@GetMapping("/order/{userId}")
	public List<OrderDto> getOrder(@PathVariable(name="userId") String userId) {

		return orderService.getOrders(userId);
	}
}
