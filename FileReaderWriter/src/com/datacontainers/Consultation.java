package com.datacontainers;

public class Consultation extends Product{
	
	
	private String technicianPersonCode = "";
	private String hourlyFee = "";
	
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

}
