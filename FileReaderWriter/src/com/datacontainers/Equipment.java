package com.datacontainers;

public class Equipment extends Product {
	
	private String pricePerUnit = "";

	public Equipment(String code, String type, String name, String pricePerUnit) {
		super(code, type, name);
		this.pricePerUnit = pricePerUnit;
		
	}
	public void setPricePerUnit(String pricePerUnite){
		this.pricePerUnit = pricePerUnite;
	}
	
	public String getPricePerUnit(){
		return this.pricePerUnit;
	}

}
