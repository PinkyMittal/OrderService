package com.pubkart.catalog.util;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.elasticsearch.index.query.BoolQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.domain.Sort.Order;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.util.MultiValueMap;

public class SearchUtils {

	private static final String SEARCH = "search";
	private static final String SORT = "sort";
	private static final String SIZE = "size";
	private static final String PAGE = "page";

	public static SearchQuery buildSearchQuery(MultiValueMap<String, String> params) {

		List<String> page = params.get(PAGE);
		List<String> size = params.get(SIZE);
		List<String> sort = params.get(SORT);
		List<String> search = params.get(SEARCH);

		List<Order> orders = new ArrayList<>();
		if (sort != null && !sort.isEmpty()) {
			for (String sorted : sort) {
				String[] fields = sorted.split(",");
				if (fields.length > 1) {
					Order order = new Order(Direction.fromString(fields[1]), fields[0]);
					orders.add(order);
				} else {
					Order order = new Order(Direction.ASC, fields[0]);
					orders.add(order);
				}
			}
		}

		Integer pageNumber = (page != null && !page.isEmpty()) ? Integer.valueOf(page.get(0)) : 0;
		Integer pageSize = (size != null && !size.isEmpty()) ? Integer.valueOf(size.get(0)) : 10;

		if (pageSize > 50) {
			throw new RuntimeException("Page size cannot be greater than 50.");
		}

		PageRequest pageRequest = PageRequest.of(pageNumber, pageSize,
				(!orders.isEmpty()) ? Sort.by(orders) : Sort.unsorted());

		BoolQueryBuilder query = QueryBuilders.boolQuery();
		if (search != null) {
			String searchString = search.get(0);

			Pattern pattern = Pattern.compile("([^,]*)(:|!)([^,]*)");
			Matcher matcher = pattern.matcher(searchString + ",");
			while (matcher.find()) {
				String field = matcher.group(1);
				String operation = matcher.group(2);
				String value = matcher.group(3);

				switch (operation) {
				case ":":
					String[] values = value.split(";");
					if (values.length < 2) {
						query.must(QueryBuilders.matchQuery(field, value));
						break;
					} else {
						BoolQueryBuilder minQuery = QueryBuilders.boolQuery();

						for (String val : values) {
							minQuery.should(QueryBuilders.matchQuery(field, val));
						}

						minQuery.minimumShouldMatch(1);
						query.must(minQuery);
						break;
					}
				case "!":
					query.mustNot(QueryBuilders.matchQuery(field, value));
					break;
				default:
					break;
				}
			}
		}
		return new NativeSearchQueryBuilder().withQuery(QueryBuilders.matchAllQuery()).withFilter(query)
				.withPageable(pageRequest).build();
	}

}
