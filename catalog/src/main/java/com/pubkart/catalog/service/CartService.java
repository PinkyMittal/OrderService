package com.pubkart.catalog.service;
import com.pubkart.catalog.model.Cart;
import com.pubkart.catalog.model.User;

public interface CartService {
	
	public Cart getCartByUser(String userId);
	public Cart updateCart(User user);
	
}