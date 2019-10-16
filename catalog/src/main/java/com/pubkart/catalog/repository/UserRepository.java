package com.pubkart.catalog.repository;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.pubkart.catalog.model.User;

public interface UserRepository extends ElasticsearchRepository<User, String>{

}
