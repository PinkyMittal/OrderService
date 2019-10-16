package com.pubcart.inventory.catalog.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import com.pubcart.inventory.catalog.model.Catalog;

@Service
public class CatalogServiceImpl implements CatalogService {

	private static final String CATALOG_SERVICE_IDENTIFIER = "catalog-service";

	@Autowired
	private RestTemplate restTemplate;

	@Override
	public void addCatalog(Catalog catalog) {
		restTemplate.exchange("http://" + CATALOG_SERVICE_IDENTIFIER + "/catalog", HttpMethod.POST,
				prepareHttpEntity(catalog), new ParameterizedTypeReference<Catalog>() {
				});

	}

	@Override
	public void updateCatalog(Catalog catalog, long id) {
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("id", id);

		restTemplate.exchange("http://" + CATALOG_SERVICE_IDENTIFIER + "/catalog/" + id, HttpMethod.PUT,
				prepareHttpEntity(catalog), new ParameterizedTypeReference<Catalog>() {
				}, params);

	}

	@Override
	public void deleteCatalog(long id) {
		Map<String, Long> params = new HashMap<String, Long>();
		params.put("id", id);

		restTemplate.exchange("http://" + CATALOG_SERVICE_IDENTIFIER + "/catalog/" + id, HttpMethod.DELETE, null,
				new ParameterizedTypeReference<Catalog>() {
				}, params);
	}

	private HttpEntity<Catalog> prepareHttpEntity(Catalog catalog) {

		MultiValueMap<String, String> headers = new LinkedMultiValueMap<String, String>();
		headers.add("Content-Type", "application/json");
		RestTemplate restTemplate = new RestTemplate();
		restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
		HttpEntity<Catalog> requestEntity = new HttpEntity<Catalog>(catalog, headers);

		return requestEntity;
	}
}
