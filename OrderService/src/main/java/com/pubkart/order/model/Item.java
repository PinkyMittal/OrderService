package com.pubkart.order.model;

import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
@Entity
public class Item {

	@Id
	private Long itemId;
	private Integer quantity;
	private Double perUnitPrice;
	private Long orderId;

	public Item() {
	}

}
