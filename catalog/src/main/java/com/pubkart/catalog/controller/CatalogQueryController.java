package com.pubkart.catalog.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pubkart.catalog.exception.ApplicationException;
import com.pubkart.catalog.model.Catalog;
import com.pubkart.catalog.service.CatalogQueryService;

@RestController
public class CatalogQueryController {

	@Autowired
	CatalogQueryService catalogQueryService;

	@GetMapping("/catalog/{id}")
	public ResponseEntity<Catalog> getCatalog(@PathVariable("id") Long id) {
		try {
			Catalog catalog = catalogQueryService.findCatalogById(id);
			return new ResponseEntity<Catalog>(catalog, HttpStatus.OK);
		} catch (ApplicationException ex) {
			return new ResponseEntity<Catalog>(ex.getStatus());
		}
	}

	@GetMapping("/catalog/search")
	public ResponseEntity<Page<Catalog>> searchCatalog(@RequestParam MultiValueMap<String, String> params) {
		return new ResponseEntity<Page<Catalog>>(catalogQueryService.searchCatalog(params), HttpStatus.OK);
	}

}
