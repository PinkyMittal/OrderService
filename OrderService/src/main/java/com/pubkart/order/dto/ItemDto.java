package com.pubkart.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ItemDto {
	private Long itemId;
	private Integer quantity;
	private Double perUnitPrice;
	
	public ItemDto() {
		
	}
}
