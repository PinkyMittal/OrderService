package com.pubcart.inventory.repo;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pubcart.inventory.model.Inventory;

@Repository
public interface InventoryRepository extends CrudRepository<Inventory, Long> {

}
