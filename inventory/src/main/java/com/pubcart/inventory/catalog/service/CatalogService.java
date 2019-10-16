package com.pubcart.inventory.catalog.service;

import com.pubcart.inventory.catalog.model.Catalog;

public interface CatalogService {

	public void addCatalog(Catalog catalog);

	public void updateCatalog(Catalog catalog, long id);

	public void deleteCatalog(long id);
}
