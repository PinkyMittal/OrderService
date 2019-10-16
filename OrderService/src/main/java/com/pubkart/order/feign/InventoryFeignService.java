package com.pubkart.order.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.pubkart.order.dto.ItemDto;

@FeignClient(name = "inventory-service", fallback = InventoryFeignServiceFallBack.class)
public interface InventoryFeignService {

	@GetMapping("/canplaceorder")
	public ResponseEntity<String> getItems(List<ItemDto> list);
	
	@PutMapping("/updateinventory")
	public ResponseEntity<String> updateInventory(List<ItemDto> list);

}
