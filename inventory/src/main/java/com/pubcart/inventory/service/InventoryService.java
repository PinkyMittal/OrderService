package com.pubcart.inventory.service;

import java.util.List;

import com.pubcart.inventory.exception.InventoryException;
import com.pubcart.inventory.model.Inventory;
import com.pubcart.inventory.model.LineItem;

public interface InventoryService {
	
	public void addItem(Inventory inventory);
	
	public void updateItem(Inventory inventory,long id)throws InventoryException;
	
	public void deleteItem(long id)throws InventoryException;
	
	public boolean isQuantityAvailable(List<LineItem> lineItems);
	
	public void updateInventory(List<LineItem> lineItems);

}
