package com.datacontainers;

public class Equipment extends Product {
	
	private String pricePerUnit = "";
	private double totalCost = 0.0;
	private String numUnits = "";
 

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
	
	public void setNumUnits(String numUnits){
		this.numUnits = numUnits;
	}
	
	public String formatting(){
		String format = "(" + this.numUnits + " units at " + "$" + this.getPricePerUnit() + "/unit" + ")";;
		return format;
	}
	
	//TODO this one is right right?
	public void setTotalCost(){
		this.totalCost = Double.parseDouble(numUnits) * Double.parseDouble(this.pricePerUnit);
	}
	public double getTotalCost(){
		return this.totalCost;
	}
	
	
	

}
