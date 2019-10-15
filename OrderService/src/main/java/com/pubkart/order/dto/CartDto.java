package com.pubkart.order.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CartDto {

	private List<ItemDto> items;
	private Double cartValue;

	public CartDto() {

	}

}
