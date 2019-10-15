package com.pubkart.order.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pubkart.order.model.Item;

public interface ItemRepository extends JpaRepository<Item, Long> {

	List<Item> findByOrderId(Long orderId);

}
