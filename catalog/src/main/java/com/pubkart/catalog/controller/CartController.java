package com.pubkart.catalog.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.pubkart.catalog.model.Cart;
import com.pubkart.catalog.model.User;
import com.pubkart.catalog.service.CartService;

@RestController
public class CartController {

	@Autowired
	CartService cartService;
	
	
	@GetMapping("/cart/{userid}")
	public ResponseEntity<Cart> getCartById(@PathVariable("userid") String userid) {
		return new ResponseEntity<Cart>(cartService.getCartByUser(userid), HttpStatus.OK) ;
	}
	
	@PutMapping("/cart")
	public ResponseEntity<Cart> updateCart(@RequestBody User user) {
		return new ResponseEntity<Cart>(cartService.updateCart(user), HttpStatus.OK);
	}
	
}
