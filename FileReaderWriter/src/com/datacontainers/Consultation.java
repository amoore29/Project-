package com.datacontainers;

public class Consultation extends Product{
	
	
	private String technicianPersonCode = "";
	private String hourlyFee = "";
	private Double totalCost = 0.0;
	private String hours = "";
	
	public Consultation(String code, String type, String name, String technicianPersonCode, String hourlyFee) {
		super(code, type, name);
		this.technicianPersonCode = technicianPersonCode;
		this.hourlyFee = hourlyFee;
	}
	
	
	public String getTechnicianPersonCode() {
		return technicianPersonCode;
	}
	public void setTechnicianPersonCode(String technicianPersonCode) {
		this.technicianPersonCode = technicianPersonCode;
	}
	public String getHourlyFee() {
		return hourlyFee;
	}
	public void setHourlyFee(String hourlyFee) {
		this.hourlyFee = hourlyFee;
	}
	
	public void setHours(String hours){
		this.hours = hours;
	}
	
	public String formatting(){
		String format = "(" + hours + " hours at " + "$" + this.getHourlyFee() + "/hr" + ")";
		return format;
	}
	
	//TODO did i do this right?
	public void setTotalCost(){
		 this.totalCost = (Double.parseDouble(hours) * Double.parseDouble(this.hourlyFee));
		
	}
	
	public Double GetTotalCost(){
		return this.totalCost;
	}
	
	
	
	
	
	
	

}
