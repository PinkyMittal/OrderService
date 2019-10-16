package com.pubkart.catalog.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import com.pubkart.catalog.exception.ApplicationException;
import com.pubkart.catalog.model.Catalog;
import com.pubkart.catalog.repository.CatalogRepository;
import com.pubkart.catalog.service.CatalogQueryService;
import com.pubkart.catalog.util.SearchUtils;

@Service
public class CatalogQueryServiceImpl implements CatalogQueryService {

	@Autowired
	CatalogRepository catalogRepository;

	@Override
	@Cacheable(value = "catalogs", key = "#id", unless = "#result == null")
	public Catalog findCatalogById(Long id) throws ApplicationException {
		Optional<Catalog> catalog = catalogRepository.findById(id);
		if (catalog.isPresent()) {
			return catalog.get();
		} else
			throw new ApplicationException("Catalog not found for id: " + id, HttpStatus.NOT_FOUND);
	}

	@Override
	public Page<Catalog> searchCatalog(MultiValueMap<String, String> params) {
		SearchQuery query = SearchUtils.buildSearchQuery(params);
		return catalogRepository.search(query);
	}

}
