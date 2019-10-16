package com.pubcart.inventory.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.pubcart.inventory.util.LiquorMeasurement;
import com.pubcart.inventory.util.LiquorOrigin;
import com.pubcart.inventory.util.LiquorType;

@Entity
public class Inventory implements Serializable {

	private static final long serialVersionUID = 643034403217593916L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name = "id")
	private long id;

	private String name;

	private String description;

	private Double price;

	private int age;

	@Enumerated(EnumType.ORDINAL)
	private LiquorType liquorType;

	@Enumerated(EnumType.ORDINAL)
	private LiquorOrigin liquorOrigin;

	private Double percentage;

	private Double unit;

	@Enumerated(EnumType.ORDINAL)
	private LiquorMeasurement liquorMeasurement;

	private int availableQuantity;

	private int totalQuantity;

	public Inventory() {
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getTotalQuantity() {
		return totalQuantity;
	}

	public void setTotalQuantity(int totalQuantity) {
		this.totalQuantity = totalQuantity;
	}

	public int getAvailableQuantity() {
		return availableQuantity;
	}

	public void setAvailableQuantity(int availableQuantity) {
		this.availableQuantity = availableQuantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public LiquorType getLiquorType() {
		return liquorType;
	}

	public void setLiquorType(LiquorType liquorType) {
		this.liquorType = liquorType;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public LiquorOrigin getLiquorOrigin() {
		return liquorOrigin;
	}

	public void setLiquorOrigin(LiquorOrigin liquorOrigin) {
		this.liquorOrigin = liquorOrigin;
	}

	public Double getPercentage() {
		return percentage;
	}

	public void setPercentage(Double percentage) {
		this.percentage = percentage;
	}

	public Double getUnit() {
		return unit;
	}

	public void setUnit(Double unit) {
		this.unit = unit;
	}

	public LiquorMeasurement getLiquorMeasurement() {
		return liquorMeasurement;
	}

	public void setLiquorMeasurement(LiquorMeasurement liquorMeasurement) {
		this.liquorMeasurement = liquorMeasurement;
	}

}
