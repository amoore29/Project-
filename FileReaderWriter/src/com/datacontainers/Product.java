package com.datacontainers;

public class Product {

	private String code = "";
	private String type = "";
	private String name = "";
	

	
	//Product constructor for products of type
	public Product(String code, String type, String name) {
		this.code = code;
		this.type = type;
		this.name = name;
	}	
	
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	
	
	
	
	
		
}
