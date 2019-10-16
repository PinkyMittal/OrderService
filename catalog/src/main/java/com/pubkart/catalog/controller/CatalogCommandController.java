package com.pubkart.catalog.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.pubkart.catalog.exception.ApplicationException;
import com.pubkart.catalog.model.Catalog;
import com.pubkart.catalog.service.CatalogCommandService;

@RestController
public class CatalogCommandController {

	@Autowired
	CatalogCommandService catalogCommandService;

	@PostMapping("/catalog")
	public ResponseEntity<Catalog> addCatalog(@RequestBody Catalog catalog) {
		return new ResponseEntity<Catalog>(catalogCommandService.addCatalog(catalog), HttpStatus.CREATED);
	}

	@PostMapping("/catalogs")
	public ResponseEntity<List<Catalog>> addCatalogs(@RequestBody List<Catalog> catalogs) {
		return new ResponseEntity<List<Catalog>>(catalogCommandService.addCatalogs(catalogs), HttpStatus.CREATED);
	}

	@DeleteMapping("/catalog/{id}")
	public ResponseEntity<Void> deleteCatalog(@PathVariable("id") Long id) {
		catalogCommandService.deleteCatalog(id);
		return new ResponseEntity<Void>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/catalog/{id}")
	@ResponseStatus(code = HttpStatus.OK)
	public ResponseEntity<Catalog> updateCatalog(@RequestBody Catalog catalog, @PathVariable("id") Long id) {
		try {
			return new ResponseEntity<Catalog>(catalogCommandService.updateCatalog(catalog, id), HttpStatus.OK);
		} catch (ApplicationException ex) {
			return new ResponseEntity<Catalog>(ex.getStatus());
		}
	}
}
