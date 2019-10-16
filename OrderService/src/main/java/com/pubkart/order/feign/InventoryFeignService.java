package com.pubkart.order.feign;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PutMapping;

import com.pubkart.order.config.FeignConfiguration;
import com.pubkart.order.dto.ItemDto;

@FeignClient(name = "inventory-service", fallback = InventoryFeignServiceFallBack.class, configuration =FeignConfiguration.class)
public interface InventoryFeignService {

	@PutMapping("/inventory/canplaceorder")
	public String getItems(List<ItemDto> list);

	@PutMapping("/inventory/updateinventory")
	public String updateInventory(List<ItemDto> list);

}
