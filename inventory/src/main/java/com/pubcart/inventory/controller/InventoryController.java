package com.pubcart.inventory.controller;

import java.util.List;

import javax.naming.directory.InvalidAttributesException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.pubcart.inventory.exception.InventoryException;
import com.pubcart.inventory.model.Inventory;
import com.pubcart.inventory.model.LineItem;
import com.pubcart.inventory.service.InventoryServiceImpl;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

	@Autowired
	private InventoryServiceImpl inventoryService;

	@RequestMapping(method = RequestMethod.POST, value = "/additem")
	public ResponseEntity<String> addItem(@RequestBody Inventory inventory) {

		inventoryService.addItem(inventory);
		return new ResponseEntity<String>("Success", HttpStatus.CREATED);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/updateitem/{id}")
	public ResponseEntity<String> updateItem(@RequestBody Inventory inventory, @PathVariable("id") Long id)
			throws InventoryException {
		inventoryService.updateItem(inventory, id);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}

	@RequestMapping(method = RequestMethod.DELETE, value = "/deleteitem/{id}")
	public ResponseEntity<String> deleteItem(@PathVariable("id") Long id)
			throws InventoryException {
		inventoryService.deleteItem(id);
		return new ResponseEntity<String>("Success", HttpStatus.NOT_FOUND);
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/canplaceorder")
	public String getCartDetails(@RequestBody List<LineItem> lineItems) {
		boolean result = inventoryService.isQuantityAvailable(lineItems);
		String response = result ? "Success" : "Failure";
		return response;
	}

	@RequestMapping(method = RequestMethod.PUT, value = "/updateinventory")
	public ResponseEntity<String> updateInventory(@RequestBody List<LineItem> lineItems)
			throws InvalidAttributesException {
		inventoryService.updateInventory(lineItems);
		return new ResponseEntity<String>("Success", HttpStatus.OK);
	}
}
