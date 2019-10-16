package com.pubkart.catalog.model;

import java.io.Serializable;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import lombok.Data;

@Document(indexName = "catalog", type = "product", createIndex = true)
@Data
public class Catalog implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private Long id;

	@Field(type = FieldType.Text)
	private String name;

	private String desc;

	@Field(type = FieldType.Keyword)
	private double price;

	@Field(type = FieldType.Keyword)
	private int age;

	@Field(type = FieldType.Keyword)
	private Type type;

	@Field(type = FieldType.Keyword)
	private Origin origin;

	@Field(type = FieldType.Keyword)
	private double percentage;

	@Field(type = FieldType.Keyword)
	private double unit;

	@Field(type = FieldType.Keyword)
	private Measurement measurement;

	@Field(type = FieldType.Keyword)
	private int availableQuantity;
}
