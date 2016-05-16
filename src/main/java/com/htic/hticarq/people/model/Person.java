package com.htic.hticarq.people.model;

public class Person {

	private long id;

	private String name;
	private String surname1;
	private String surname2;

	private PostalAddress postalAddress;
	private SimplePostalAddress simplePostalAddress;


	//Getters && Setters
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname1() {
		return surname1;
	}
	public void setSurname1(String surname1) {
		this.surname1 = surname1;
	}
	public String getSurname2() {
		return surname2;
	}
	public void setSurname2(String surname2) {
		this.surname2 = surname2;
	}
	public PostalAddress getPostalAddress() {
		return postalAddress;
	}
	public void setPostalAddress(PostalAddress postalAddress) {
		this.postalAddress = postalAddress;
	}
	public SimplePostalAddress getSimplePostalAddress() {
		return simplePostalAddress;
	}
	public void setSimplePostalAddress(SimplePostalAddress simplePostalAddress) {
		this.simplePostalAddress = simplePostalAddress;
	}
}