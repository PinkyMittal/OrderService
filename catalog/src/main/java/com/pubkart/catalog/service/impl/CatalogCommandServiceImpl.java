package com.pubkart.catalog.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;
import com.pubkart.catalog.exception.ApplicationException;
import com.pubkart.catalog.model.Catalog;
import com.pubkart.catalog.repository.CatalogRepository;
import com.pubkart.catalog.service.CatalogCommandService;

@Service
public class CatalogCommandServiceImpl implements CatalogCommandService {

	@Autowired
	CatalogRepository catalogRepository;

	@Override
	public Catalog addCatalog(Catalog catalog) {
		return catalogRepository.save(catalog);
	}

	@Override
	public List<Catalog> addCatalogs(List<Catalog> catalogs) {
		return Lists.newArrayList(catalogRepository.saveAll(catalogs));
	}

	@Override
	@CachePut(value = "catalogs", key = "#id", unless = "#result == null")
	public Catalog updateCatalog(Catalog catalog, Long id) throws ApplicationException {
		boolean exists = catalogRepository.existsById(id);
		if (exists) {
			return catalogRepository.save(catalog);
		} else
			throw new ApplicationException("Product not found.", HttpStatus.NOT_FOUND);
	}

	@Override
	@CacheEvict(value = "catalogs", key = "#id")
	public void deleteCatalog(Long id) {
		catalogRepository.deleteById(id);
		;
	}

}
