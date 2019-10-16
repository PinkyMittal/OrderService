package com.pubkart.catalog.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import com.github.vanroy.springdata.jest.JestElasticsearchTemplate;

import io.searchbox.client.JestClient;

@Configuration
public class AppConfig {

	@Autowired
	JestClient jestClient;

	public JestElasticsearchTemplate elasticsearchTemplate() {
		return new JestElasticsearchTemplate(jestClient);
	}

}
