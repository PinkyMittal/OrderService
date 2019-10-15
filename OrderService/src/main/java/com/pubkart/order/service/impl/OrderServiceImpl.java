package com.pubkart.order.service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pubkart.order.dto.ItemDto;
import com.pubkart.order.dto.OrderDto;
import com.pubkart.order.dto.UserDto;
import com.pubkart.order.feign.InventoryFeignService;
import com.pubkart.order.feign.PaymentFeignService;
import com.pubkart.order.model.Item;
import com.pubkart.order.model.Order;
import com.pubkart.order.model.OrderStatus;
import com.pubkart.order.model.PaymentResponse;
import com.pubkart.order.model.PaymentStatus;
import com.pubkart.order.repository.ItemRepository;
import com.pubkart.order.repository.OrderRepository;
import com.pubkart.order.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private static final String PASS = "PASS";
	@Autowired
	InventoryFeignService inventoryFeignService;

	@Autowired
	PaymentFeignService paymentFeignService;

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	ItemRepository itemRepository;

	@Override
	public OrderDto createOrder(UserDto user) {
//		Set<LineItem> items = new Hashset<LineItem>(user.getCart().getItems());
//		ResponseEntity<String> result = inventoryFeignService.getItems(items);

//		if (result.equals(PASS)) {
		OrderDto order = saveInitialOrder(user);
//			makePayment(cart, order);
		return order;
//		} else {
//			return result.getBody();
//		}

	}
	
	@Override
	public List<OrderDto> getOrders(String  userId) {
		List<OrderDto> orderDtos = getOrderDto(orderRepository.findOrderByUserId(userId));
		return orderDtos;
	}

	/*
	 * private void makePayment(Cart cart, Order order) { Payment payment = new
	 * Payment(order.getUserId(), order.getId(), cart.getCartValue(), null);
	 * PaymentResponse response = paymentFeignService.makePayment(payment); if
	 * (PaymentStatus.SUCCESS.equals(response.getStatus())) {
	 * updateOrderWhenPaymentIsSuccessFull(order, response); } else {
	 * updateOrderWhenPaymentFails(order, response); } }
	 */

	private List<OrderDto> getOrderDto(List<Order> orders) {
		List<OrderDto> orderDtos = new ArrayList<OrderDto>();
		Iterator<Order> it = orders.iterator();
		while(it.hasNext()) 
		{
			Order order = it.next();
			List<Item> saveditems = itemRepository.findByOrderId(order.getId());
			OrderDto orderDto = getOrderDto(order, saveditems);
			orderDtos.add(orderDto);
			
			
		}
		return orderDtos;
		
	}

	private OrderDto getOrderDto(Order order, List<Item> items) {
		return new OrderDto(order.getId(), order.getPaymentId(), order.getPaymentStatus(), order.getOrderStatus(), order.getOrderDate(), populateItemDtos(items), order.getUserId(), order.getOrderValue());
	}

	private void updateOrderWhenPaymentFails(Order order, PaymentResponse response) {
		order.setPaymentStatus(PaymentStatus.FAILURE);
		order.setOrderStatus(OrderStatus.FAILURE);
		order.setPaymentId(response.getTransactionId());
		orderRepository.save(order);
	}

	private void updateOrderWhenPaymentIsSuccessFull(Order order, PaymentResponse response) {
		order.setPaymentStatus(PaymentStatus.SUCCESS);
		order.setOrderStatus(OrderStatus.SUCCESS);
		order.setPaymentId(response.getTransactionId());
		orderRepository.save(order);
	}

	private OrderDto saveInitialOrder(UserDto userDto) {
		
		Order order = new Order();
		order.setOrderDate(new Date(new java.util.Date().getTime()));
		order.setOrderValue(userDto.getCart().getCartValue());
        order.setUserId(userDto.getUserId());
        order = orderRepository.save(order);
	    List<ItemDto> itemDtos = userDto.getCart().getItems();
		List<Item> items = populateItems(itemDtos, order);
		items.stream().forEach(item -> itemRepository.save(item));
		//orderRepository.save(order);
		List<Item> saveditems = itemRepository.findByOrderId(order.getId());
		Order updatedorder = orderRepository.save(order);
		OrderDto orderDto = getOrderDto(order, saveditems);
		//orderDto.setItems(itemDtos);
		return orderDto;
	}

	private List<Item> populateItems(List<ItemDto> itemsDto, final Order order) {
		List<Item> items = new ArrayList<Item>();
		itemsDto.stream().forEach(itemDto -> items.add(new Item(itemDto.getItemId(), itemDto.getQuantity(), itemDto.getPerUnitPrice(), order.getId())));
		return items;
	}
	
	private List<ItemDto> populateItemDtos(List<Item> items) {
		List<ItemDto> itemDtos = new ArrayList<ItemDto>();
		items.stream().forEach(item -> itemDtos.add(new ItemDto(item.getItemId(), item.getQuantity(), item.getPerUnitPrice())));
		return itemDtos;
	}

}
