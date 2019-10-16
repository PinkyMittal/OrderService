package com.pubkart.catalog.model;

import java.util.List;

import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

@Data
public class Cart {

	@Field(type = FieldType.Nested, includeInParent = true)
	private List<LineItem> items;

	private double cartValue;

	public Cart() {
	}

}
