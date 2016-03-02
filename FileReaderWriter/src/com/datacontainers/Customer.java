package com.datacontainers;

public class Customer {
	
	private String customerCode = "";
	private String typeOfCustomer = "";
	private Persons primaryContact;
	private String name = "";
	private String address = "";
	
	
	public Customer(String customerCode, String typeOfCustomer, Persons primaryContact, String name, String address) {
		this.customerCode = customerCode;
		this.typeOfCustomer = typeOfCustomer;
		this.primaryContact = primaryContact;
		this.name = name;
		this.address = address;
	}
	
	public String getCustomerCode() {
		return customerCode;
	}
	public void setCustomerPerson(String customerCode) {
		this.customerCode = customerCode;
	}
	public String getTypeOfCustomer() {
		return typeOfCustomer;
	}
	public void setTypeOfCustomer(String typeOfCustomer) {
		this.typeOfCustomer = typeOfCustomer;
	}
	public Persons getPrimaryContact() {
		return primaryContact;
	}
	public void setPrimaryContact(Persons primaryContact) {
		this.primaryContact = primaryContact;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	

}
