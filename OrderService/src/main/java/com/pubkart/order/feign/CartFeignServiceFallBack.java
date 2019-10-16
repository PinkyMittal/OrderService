package com.pubkart.order.feign;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import com.pubkart.order.dto.CartDto;
import com.pubkart.order.dto.UserDto;

@Component
public class CartFeignServiceFallBack implements CatalogFeignService {

	@Override
	public ResponseEntity<CartDto> notifyCatalog(UserDto userDto) {

		ResponseEntity<CartDto> cart = new ResponseEntity<CartDto>(HttpStatus.NOT_FOUND);
		return cart;
	}

}
