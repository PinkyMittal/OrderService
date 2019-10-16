package com.pubkart.order.service.impl;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.pubkart.order.dto.CartDto;
import com.pubkart.order.dto.ItemDto;
import com.pubkart.order.dto.OrderDto;
import com.pubkart.order.dto.UserDto;
import com.pubkart.order.feign.CatalogFeignService;
import com.pubkart.order.feign.InventoryFeignService;
import com.pubkart.order.feign.PaymentFeignService;
import com.pubkart.order.model.Item;
import com.pubkart.order.model.Order;
import com.pubkart.order.model.OrderStatus;
import com.pubkart.order.model.Payment;
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
	
	@Autowired
	CatalogFeignService catalogFeignService;

	@Override
	public OrderDto createOrder(UserDto user) {
		List<ItemDto> items = user.getCart().getItems();
		ResponseEntity<String> result = inventoryFeignService.getItems(items);

		if (result.equals(PASS)) {
			Order order = saveInitialOrder(user);
			makePayment(user.getCart(), order);
			return getOrderDto(order);
		} else {
			return getFailedOrder(user);
		}

	}

	private OrderDto getFailedOrder(UserDto user) {
		OrderDto orderDto = new OrderDto();
		orderDto.setUserId(user.getUserId());
		orderDto.setOrderStatus(OrderStatus.FAILURE);
		return orderDto;
	}

	@Override
	public List<OrderDto> getOrders(String userId) {
		List<OrderDto> orderDtos = getOrderDto(orderRepository.findOrderByUserId(userId));
		return orderDtos;
	}

	private void makePayment(CartDto cart, Order order) {
		Payment payment = new Payment(order.getUserId(), order.getId(), cart.getCartValue(), null);
		PaymentResponse response = paymentFeignService.makePayment(payment);
		if (PaymentStatus.SUCCESS.equals(response.getStatus())) {
			updateInventory(cart.getItems());
			updateOrderWhenPaymentIsSuccessFull(order, response);
			notifyCatalog(order.getUserId());
		} else {
			updateOrderWhenPaymentFails(order, response);
		}
	}

	private void notifyCatalog(String userId) {
		catalogFeignService.notifyCatalog(new UserDto(userId,new CartDto()));
	}

	private void updateInventory(List<ItemDto> items) {
		inventoryFeignService.updateInventory(items);
	}

	private List<OrderDto> getOrderDto(List<Order> orders) {
		List<OrderDto> orderDtos = new ArrayList<OrderDto>();
		Iterator<Order> it = orders.iterator();
		while (it.hasNext()) {
			Order order = it.next();
			OrderDto orderDto = getOrderDto(order);
			orderDtos.add(orderDto);
		}
		return orderDtos;

	}

	private OrderDto getOrderDto(Order order) {
		List<Item> saveditems = itemRepository.findByOrderId(order.getId());
		return new OrderDto(order.getId(), order.getPaymentId(), order.getPaymentStatus(), order.getOrderStatus(),
				order.getCreateDateTime(), populateItemDtos(saveditems), order.getUserId(), order.getOrderValue());
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

	private Order saveInitialOrder(UserDto userDto) {
		Order order = new Order();
		order.setOrderValue(userDto.getCart().getCartValue());
		order.setUserId(userDto.getUserId());
		order = orderRepository.save(order);
		List<ItemDto> itemDtos = userDto.getCart().getItems();
		List<Item> items = populateItems(itemDtos, order);
		items.stream().forEach(item -> itemRepository.save(item));
		Order updatedorder = orderRepository.save(order);
		return updatedorder;
	}

	private List<Item> populateItems(List<ItemDto> itemsDto, final Order order) {
		List<Item> items = new ArrayList<Item>();
		itemsDto.stream().forEach(itemDto -> items
				.add(new Item(itemDto.getItemId(), itemDto.getQuantity(), itemDto.getPerUnitPrice(), order.getId())));
		return items;
	}

	private List<ItemDto> populateItemDtos(List<Item> items) {
		List<ItemDto> itemDtos = new ArrayList<ItemDto>();
		items.stream().forEach(
				item -> itemDtos.add(new ItemDto(item.getItemId(), item.getQuantity(), item.getPerUnitPrice())));
		return itemDtos;
	}

}
