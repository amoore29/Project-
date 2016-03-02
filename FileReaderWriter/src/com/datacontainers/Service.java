package com.datacontainers;

public class Service extends Product {
	
	String activationFee = "";
	String annualFee = "";

	public Service(String code, String type, String name, String activationFee,String annaulFee) {
		super(code, type, name);
		this.activationFee = activationFee;
		this.annualFee = annaulFee;
		
	}

	public String getActivationFee() {
		return activationFee;
	}

	public void setActivationFee(String activationFee) {
		this.activationFee = activationFee;
	}

	public String getAnnualFee() {
		return annualFee;
	}

	public void setAnnualFee(String annualFee) {
		this.annualFee = annualFee;
	}

	
	
}
