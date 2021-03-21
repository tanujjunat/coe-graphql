package com.graphql.coe.entities;

public class CoffeeType {

	String id;
	String name;

	public CoffeeType(String name) {
		this(null, name);
	}

	public CoffeeType(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
