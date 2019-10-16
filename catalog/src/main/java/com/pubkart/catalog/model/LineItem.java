package com.pubkart.catalog.model;

import lombok.Data;

@Data
public class LineItem {
	
	private Long itemId;
	private Integer quantity;
	private Double perUnitPrice;
	
	public LineItem() {
	}
	

}
