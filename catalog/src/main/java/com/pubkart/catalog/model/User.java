package com.pubkart.catalog.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import lombok.Data;

@Data
@Document(indexName = "user", type = "user")
public class User {
	
	@Id
	private String userId;
	private Cart cart;
	
	public User() {
	}

}
