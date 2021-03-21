package com.graphql.coe.entities;

public class Order {
	
	private final String id;
	private final String state;
	private final String brewState;
	private final String coffeeType;
	private final String beanOrigin;
	private final String date;
	
	public Order(String state, String brewState, String coffeeType,
			String beanOrigin, String date) {
		this(null, state, brewState, coffeeType, beanOrigin, date);
	}

	public Order(String id, String state, String brewState, String coffeeType,
			String beanOrigin, String date) {
		this.id = id;
		this.state = state;
		this.brewState = brewState;
		this.coffeeType = coffeeType;
		this.beanOrigin = beanOrigin;
		this.date = date;
	}

	public String getId() {
		return id;
	}

	public String getState() {
		return state;
	}

	public String getBrewState() {
		return brewState;
	}

	public String getCoffeeType() {
		return coffeeType;
	}

	public String getBeanOrigin() {
		return beanOrigin;
	}

	public String getDate() {
		return date;
	}

	

}
