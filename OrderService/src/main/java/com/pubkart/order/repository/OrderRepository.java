package com.pubkart.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pubkart.order.model.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {

	public List<Order> findOrderByUserId(String userId);
}
