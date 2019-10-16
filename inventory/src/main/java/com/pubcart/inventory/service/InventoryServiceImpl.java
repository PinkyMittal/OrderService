package com.pubcart.inventory.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pubcart.inventory.catalog.model.Catalog;
import com.pubcart.inventory.catalog.model.Measurement;
import com.pubcart.inventory.catalog.model.Origin;
import com.pubcart.inventory.catalog.model.Type;
import com.pubcart.inventory.catalog.service.CatalogService;
import com.pubcart.inventory.exception.InventoryException;
import com.pubcart.inventory.model.Inventory;
import com.pubcart.inventory.model.LineItem;
import com.pubcart.inventory.repo.InventoryRepository;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	@Autowired
	private CatalogService catalogService;

	@Override
	public void addItem(Inventory inventory) {
		inventoryRepository.save(inventory);
		catalogService.addCatalog(prepareCatalog(inventory));
	}

	@Override
	public void updateItem(Inventory inventory, long id) throws InventoryException {
		if (inventoryRepository.exists(id)) {
			Inventory databaseObject = inventoryRepository.findOne(id);
			inventory.setId(databaseObject.getId());
			inventoryRepository.save(inventory);
			catalogService.updateCatalog(prepareCatalog(inventory), id);
		} else {
			throw new InventoryException("Id does not exist");
		}
	}

	@Override
	public void deleteItem(long id) throws InventoryException {
		if (inventoryRepository.exists(id)) {
			inventoryRepository.delete(id);
			catalogService.deleteCatalog(id);
		} else {
			throw new InventoryException("Id does not exist");
		}

	}

	@Override
	public boolean isQuantityAvailable(List<LineItem> lineItems) {
		for (LineItem item : lineItems) {
			Inventory inventory = inventoryRepository.findOne(item.getItemId());
			if (inventory.getAvailableQuantity() < item.getQuantity()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void updateInventory(List<LineItem> lineItems) {

		List<Long> itemIds = lineItems.stream().map(item -> item.getItemId()).collect(Collectors.toList());
		Iterable<Inventory> inventoryInstances = inventoryRepository.findAll(itemIds);
		inventoryInstances.forEach(inventory -> lineItems.forEach(item -> {
			if (item.getItemId() == inventory.getId()) {
				updateQuantity(inventory, item);
			}
		}));

		inventoryRepository.save(inventoryInstances);
	}

	private void updateQuantity(Inventory inventory, LineItem item) {
		int quantityInInventory = inventory.getAvailableQuantity();
		if (quantityInInventory >= item.getQuantity()) {
			inventory.setAvailableQuantity(quantityInInventory - item.getQuantity());
		} else {
			throw new RuntimeException("Quantity ordered greater than inventory");
		}
	}

	private Catalog prepareCatalog(Inventory inventory) {
		Catalog catalog = new Catalog();

		catalog.setId(inventory.getId());
		catalog.setName(inventory.getName());
		catalog.setDesc(inventory.getDescription());
		catalog.setPrice(inventory.getPrice());
		catalog.setAge(inventory.getAge());
		catalog.setType(Type.valueOf(inventory.getLiquorType().name()));
		catalog.setOrigin(Origin.valueOf(inventory.getLiquorOrigin().name()));
		catalog.setPercentage(inventory.getPercentage());
		catalog.setUnit(inventory.getUnit());
		catalog.setMeasurement(Measurement.valueOf(inventory.getLiquorMeasurement().name()));
		return catalog;
	}
}
