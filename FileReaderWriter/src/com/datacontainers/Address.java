package com.datacontainers;

public class Address extends Persons {

	

	private String street;
	private String city;
	private String state;
	private String zip;
	private String country;
	
	
	//Constructor for the address class
	public Address(String id, String firstName, String lastName, String emailAddress, String secondEmail, String name,
			String address, String street, String City, String State,String zip, String country) {
		super(id, firstName, lastName, emailAddress, secondEmail, name, address);
		this.street = street;
		this.city = city;
		this.state = state;
		this.zip = zip;
		this.country = country;		
		
		
		
		// TODO Auto-generated constructor stub
	}	
	
	
	//Setters and getter for every variable
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCountry() {
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	

}
