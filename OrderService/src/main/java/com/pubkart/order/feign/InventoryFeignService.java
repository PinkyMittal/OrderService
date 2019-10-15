package com.pubkart.order.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

import com.pubkart.order.model.Item;

@FeignClient(name = "inventory-service",fallback = InventoryFeignServiceFallBack.class)
public interface InventoryFeignService {

	@GetMapping("/getCartItems")
	public ResponseEntity<String> getItems(List<Item> list);
	
}
