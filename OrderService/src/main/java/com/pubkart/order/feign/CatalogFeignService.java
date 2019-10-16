package com.pubkart.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;

import com.pubkart.order.dto.CartDto;
import com.pubkart.order.dto.UserDto;

@FeignClient(name = "catalog-service", fallback = CatalogFeignServiceFallBack.class)
public interface CatalogFeignService {

	@PostMapping("/updateCart")
	public ResponseEntity<CartDto> notifyCatalog(UserDto userDto);

}
