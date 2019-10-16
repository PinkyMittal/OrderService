package com.pubkart.catalog.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

import com.pubkart.catalog.model.Catalog;

@Repository
public interface CatalogRepository extends ElasticsearchRepository<Catalog, Long> {

}
