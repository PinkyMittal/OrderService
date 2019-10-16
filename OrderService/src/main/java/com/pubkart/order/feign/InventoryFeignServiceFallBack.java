package com.pubkart.order.feign;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.pubkart.order.dto.ItemDto;

@Component
public class InventoryFeignServiceFallBack implements InventoryFeignService {

	@Override
	public ResponseEntity<String> getItems(List<ItemDto> list) {
		ResponseEntity<String> response = new ResponseEntity<String>("Order Failed", HttpStatus.NOT_FOUND);
		return response;
	}

	@Override
	public ResponseEntity<String> updateInventory(List<ItemDto> list) {
		ResponseEntity<String> response = new ResponseEntity<String>("Update Inventory Failed", HttpStatus.NOT_FOUND);
		return response;
	}

}
