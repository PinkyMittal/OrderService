package com.pubkart.order.feign;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pubkart.order.dto.ItemDto;

@Component
public class InventoryFeignServiceFallBack implements InventoryFeignService {

	private static final String UPDATE_INVENTORY_FAILED = "Update Inventory Failed";
	private static final String ORDER_FAILED = "ORDER FAILED";

	@Override
	public String getItems(List<ItemDto> list) {
		return ORDER_FAILED;
	}

	@Override
	public String updateInventory(List<ItemDto> list) {
		return UPDATE_INVENTORY_FAILED;
	}

}
