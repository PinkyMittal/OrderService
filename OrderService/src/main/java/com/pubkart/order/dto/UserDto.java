package com.pubkart.order.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserDto {
	
	
	private String userId;
	private CartDto cart;
	
	public UserDto() {}

}
