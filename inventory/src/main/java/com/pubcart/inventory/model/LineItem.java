package com.pubcart.inventory.model;

import java.io.Serializable;

public class LineItem implements Serializable {

	private static final long serialVersionUID = -1717513927611295300L;

	private Long itemId;
	private Integer quantity;
	private Double perUnitPrice;

	public LineItem() {
	}

	public Long getItemId() {
		return itemId;
	}

	public void setItemId(Long lineItemId) {
		this.itemId = lineItemId;
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPerUnitPrice() {
		return perUnitPrice;
	}

	public void setPerUnitPrice(Double perUnitPrice) {
		this.perUnitPrice = perUnitPrice;
	}

	public LineItem(Long lineItemId, Integer quantity, Double perUnitPrice) {
		super();
		this.itemId = lineItemId;
		this.quantity = quantity;
		this.perUnitPrice = perUnitPrice;
	}
}
