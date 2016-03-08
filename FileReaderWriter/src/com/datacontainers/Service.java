package com.datacontainers;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import org.joda.time.DateTime;
import org.joda.time.Days;

public class Service extends Product {
	
	private String activationFee = "";
	private String annualFee = "";
	private double totalCost = 0.0;
	private String date = "";
	
	public Service(String code, String type, String name, String activationFee,String annaulFee, String date) {
		super(code, type, name);
		this.activationFee = activationFee;
		this.annualFee = annaulFee;
		this.date = date;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
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
	
	public static double getDays(String date) throws ParseException {
		String dateParts[] = date.split(":");
		String date1 = dateParts[0];
		String date2 = dateParts[1];
		String[] d1parts = date1.split("-");
		String[] d2parts = date2.split("-");
		DateTime dt1 = new DateTime(Integer.parseInt(d1parts[0]), (Integer.parseInt(d1parts[1])), (Integer.parseInt(d1parts[2])), 0, 0, 0, 0);
		DateTime dt2 = new DateTime(Integer.parseInt(d2parts[0]), (Integer.parseInt(d2parts[1])), (Integer.parseInt(d2parts[2])), 0, 0, 0, 0);
		int days = Days.daysBetween(dt1, dt2).getDays();
		return days;
	}
	
	//Formats the information for printing purposes
	public String formatting() throws ParseException{
		String format = "(" + Service.getDays(this.date) + " days at " + "$" + this.annualFee + "/yr" + ")";
		return format;
	}
	
	//TODO Sets the total cost
	public void setTotalCost() throws NumberFormatException, ParseException{
		this.totalCost = ((Service.getDays(this.date)/365) * Double.parseDouble(this.annualFee)) + Double.parseDouble(this.activationFee);
	}
	
	public Double getTotalCost(){
		return this.totalCost;
	}
	
}
