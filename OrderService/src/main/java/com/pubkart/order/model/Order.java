package com.pubkart.order.model;

import java.sql.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@AllArgsConstructor
@ToString
@Entity
@Table(name = "order_table")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String paymentId;
	@Enumerated(EnumType.STRING)
	private PaymentStatus paymentStatus;
	@Enumerated(EnumType.STRING)
	private OrderStatus orderStatus;
	private Date orderDate;
	@Column(name = "user_id")
	private String userId;
	
	private Double orderValue;

	public Order() {

	}

}
