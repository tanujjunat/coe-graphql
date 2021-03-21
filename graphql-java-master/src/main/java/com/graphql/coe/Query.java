package com.graphql.coe;

import java.lang.reflect.Type;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.reflect.TypeToken;
import com.graphql.coe.entities.ApplicationConstants;
import com.graphql.coe.entities.Customer;
import com.graphql.coe.entities.Link;
import com.graphql.coe.repository.LinkRepository;

/**
 * Query root. Contains top-level queries.
 */
class Query implements GraphQLRootResolver {

	private final LinkRepository linkRepository;

	public Query(LinkRepository linkRepository) {
		this.linkRepository = linkRepository;
	}

	public List<Link> allLinks(LinkFilter filter, Number skip, Number first) {
		return linkRepository.getAllLinks(filter, skip.intValue(),
				first.intValue());
	}

	public List<Customer> allCustomers(CustomerFilter filter) {

		final String uri = ApplicationConstants.apiUrl;
		List<Customer> customers = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> result = restTemplate.exchange(uri,
					HttpMethod.GET, null, String.class);

			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonArray object = (JsonArray) parser.parse(result.getBody());
			Type listType = new TypeToken<List<Customer>>() {
			}.getType();
			customers = gson.fromJson(object, listType);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception : " + e.getMessage());
		}

		if (filter != null) {
			String firstNamePattern = filter.getFirstnameContains();
			String lastnamePattern = filter.getLastnameContains();

			if (firstNamePattern != null) {
				customers = customers
						.stream()
						.filter(x -> x.getFirstName().matches(
								".*" + firstNamePattern + ".*"))
						.collect(Collectors.toList());
			}

			if (lastnamePattern != null) {
				customers = customers
						.stream()
						.filter(x -> x.getLastName().matches(
								".*" + lastnamePattern + ".*"))
						.collect(Collectors.toList());
			}

		}

		return customers;
	}

	public Customer oneCustomer(CustomerFilter filter) {

		String customerId = filter.getCustomerId();

		final String uri = ApplicationConstants.apiUrl + "/" + customerId;
		Customer customer = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			ResponseEntity<String> result = restTemplate.exchange(uri,
					HttpMethod.GET, null, String.class);

			Gson gson = new Gson();
			JsonParser parser = new JsonParser();
			JsonObject object = (JsonObject) parser.parse(result.getBody());
			customer = gson.fromJson(object, Customer.class);

		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("Exception : " + e.getMessage());
		}

		return customer;
	}

}
