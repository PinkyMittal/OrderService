package com.pubkart.order.feign;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.pubkart.order.model.Item;

@Component
public class InventoryFeignServiceFallBack implements InventoryFeignService {

	@Override
	public ResponseEntity<String> getItems(List<Item> list) {
		
		ResponseEntity<String> response = new ResponseEntity<String>("Order Failed", HttpStatus.NOT_FOUND);
		return response;
	}

}
