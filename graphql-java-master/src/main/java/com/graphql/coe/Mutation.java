package com.graphql.coe;

import graphql.GraphQLException;
import graphql.schema.DataFetchingEnvironment;

import java.time.Instant;
import java.time.ZoneOffset;
import java.util.Arrays;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import com.coxautodev.graphql.tools.GraphQLRootResolver;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.graphql.coe.entities.ApplicationConstants;
import com.graphql.coe.entities.AuthData;
import com.graphql.coe.entities.Customer;
import com.graphql.coe.entities.Link;
import com.graphql.coe.entities.SigninPayload;
import com.graphql.coe.entities.User;
import com.graphql.coe.entities.Vote;
import com.graphql.coe.repository.LinkRepository;
import com.graphql.coe.repository.UserRepository;
import com.graphql.coe.repository.VoteRepository;

/**
 * Mutation root
 */
public class Mutation implements GraphQLRootResolver {

	private final LinkRepository linkRepository;
	private final UserRepository userRepository;
	private final VoteRepository voteRepository;

	public Mutation(LinkRepository linkRepository,
			UserRepository userRepository, VoteRepository voteRepository) {
		this.linkRepository = linkRepository;
		this.userRepository = userRepository;
		this.voteRepository = voteRepository;
	}

	public Link createLink(String url, String description,
			DataFetchingEnvironment env) {
		AuthContext context = env.getContext();
		Link newLink = new Link(url, description, context.getUser().getId());
		linkRepository.saveLink(newLink);
		return newLink;
	}

	public User createUser(String name, AuthData auth) {
		User newUser = new User(name, auth.getEmail(), auth.getPassword());
		return userRepository.saveUser(newUser);
	}

	public SigninPayload signinUser(AuthData auth) {
		User user = userRepository.findByEmail(auth.getEmail());
		if (user.getPassword().equals(auth.getPassword())) {
			return new SigninPayload(user.getId(), user);
		}
		throw new GraphQLException("Invalid credentials");
	}

	public Vote createVote(String linkId, String userId) {
		return voteRepository.saveVote(new Vote(Instant.now().atZone(
				ZoneOffset.UTC), userId, linkId));
	}

	public Customer createCustomer(String dob, String emailId,
			String firstName, String lastName, String mobile) {

		Customer customerRequest = new Customer(dob, emailId, firstName,
				lastName, mobile);
		Gson gson = new Gson();
		final String uri = ApplicationConstants.apiUrl;
		Customer customer = null;
		try {
			RestTemplate restTemplate = new RestTemplate();
			
			HttpHeaders requestHeaders = new HttpHeaders();
	        requestHeaders.setContentType(MediaType.APPLICATION_JSON);
	        requestHeaders.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
	        HttpEntity<Customer> requestEntity = new HttpEntity<>(customerRequest, requestHeaders);
			
			ResponseEntity<String> result = restTemplate.exchange(uri,
					HttpMethod.POST, requestEntity, String.class);

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
