package com.pubkart.catalog.service;

import org.springframework.data.domain.Page;
import org.springframework.util.MultiValueMap;

import com.pubkart.catalog.exception.ApplicationException;
import com.pubkart.catalog.model.Catalog;

public interface CatalogQueryService {

	Catalog findCatalogById(Long id) throws ApplicationException;

	Page<Catalog> searchCatalog(MultiValueMap<String, String> params);

}
