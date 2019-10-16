package com.pubkart.order.feign;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;

import com.pubkart.order.config.FeignConfiguration;
import com.pubkart.order.dto.CartDto;
import com.pubkart.order.dto.UserDto;

@FeignClient(name = "catalog-service", fallback = CatalogFeignServiceFallBack.class,configuration = FeignConfiguration.class)
public interface CatalogFeignService {

	@PutMapping("/cart")
	public ResponseEntity<CartDto> notifyCatalog(UserDto userDto);

}
