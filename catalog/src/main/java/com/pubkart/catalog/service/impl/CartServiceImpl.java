package com.pubkart.catalog.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pubkart.catalog.model.Cart;
import com.pubkart.catalog.model.User;
import com.pubkart.catalog.repository.UserRepository;
import com.pubkart.catalog.service.CartService;

@Service
public class CartServiceImpl implements CartService{

	@Autowired
	UserRepository userRepository;

	@Override
	public Cart getCartByUser(String userId) {
		
		return userRepository.findById(userId).get().getCart();
	}

	@Override
	public Cart updateCart(User user) {
		return userRepository.save(user).getCart();
	}

}
