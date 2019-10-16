package com.pubkart.order.feign;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.pubkart.order.dto.ItemDto;

@Component
public class InventoryFeignServiceFallBack implements InventoryFeignService {

	private static final String UPDATE_INVENTORY_FAILED = "Update Inventory Failed";
	private static final String ORDER_FAILED = "ORDER FAILED";

	@Override
	public ResponseEntity<String> getItems(List<ItemDto> list) {
		ResponseEntity<String> response = new ResponseEntity<String>(ORDER_FAILED, HttpStatus.NOT_FOUND);
		return response;
	}

	@Override
	public ResponseEntity<String> updateInventory(List<ItemDto> list) {
		ResponseEntity<String> response = new ResponseEntity<String>(UPDATE_INVENTORY_FAILED, HttpStatus.NOT_FOUND);
		return response;
	}

}
