package com.pubkart.catalog.service;

import java.util.List;

import com.pubkart.catalog.exception.ApplicationException;
import com.pubkart.catalog.model.Catalog;

public interface CatalogCommandService {

	Catalog addCatalog(Catalog catalog);

	List<Catalog> addCatalogs(List<Catalog> catalogs);

	Catalog updateCatalog(Catalog catalog, Long id) throws ApplicationException;

	void deleteCatalog(Long id);

}
