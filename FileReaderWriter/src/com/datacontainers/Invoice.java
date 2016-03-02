package com.datacontainers;

import java.awt.Component;
import java.util.ArrayList;

public class Invoice {

	String invoiceCode = ""; 
	Customer customer;
	String dateOfInvoice = "";
	Persons person;
	ArrayList<Product> products = new ArrayList<Product>();
	ArrayList<String> productInformation = new ArrayList<String>();
	
	
	public Invoice(String invoiceCode, Customer customer, String dateOfInvoice, Persons salesPersonCode,
			ArrayList<Product> products, ArrayList<String> productInfo) {
		this.invoiceCode = invoiceCode;
		this.customer = customer;
		this.dateOfInvoice = dateOfInvoice;
		this.person = salesPersonCode;
		this.products = products;
		this.productInformation = productInfo;
	}



	public String getInvoiceCode() {
		return invoiceCode;
	}


	public void setInvoiceCode(String invoiceCode) {
		this.invoiceCode = invoiceCode;
	}


	public Customer getCustomer() {
		return customer;
	}


	public void setCustomer(Customer customer) {
		this.customer = customer;
	}


	public String getDateOfInvoice() {
		return dateOfInvoice;
	}


	public void setDateOfInvoice(String dateOfInvoice) {
		this.dateOfInvoice = dateOfInvoice;
	}


	public Persons getPerson() {
		return person;
	}


	public void setPerson(Persons salesPersonCode) {
		this.person = salesPersonCode;
	}



	public ArrayList<Product> getProducts() {
		return products;
	}



	public void setProducts(ArrayList<Product> products) {
		this.products = products;
	}



	public ArrayList<String> getProductInformation() {
		return productInformation;
	}



	public void setProductInformation(ArrayList<String> productInformation) {
		this.productInformation = productInformation;
	}





	
	
	
	
}
