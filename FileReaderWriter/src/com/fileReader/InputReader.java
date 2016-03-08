package com.fileReader;
import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;
import java.lang.Math;
import java.text.ParseException;

import com.datacontainers.Address;
import com.datacontainers.Consultation;
import com.datacontainers.Customer;
import com.datacontainers.Equipment;
import com.datacontainers.Invoice;
import com.datacontainers.Persons;
import com.datacontainers.Product;
import com.datacontainers.Service;

//import org.cinco.payroll.Employee;

public class InputReader {
	
	public static ArrayList<Persons> persons = new ArrayList<Persons>();
	public static ArrayList<Customer> customer = new ArrayList<Customer>();
	public static ArrayList<Product> products = new ArrayList<Product>();
	public static ArrayList<Invoice> invoice = new ArrayList<Invoice>();
	
	//Constructs an array list from the persons file
	public static ArrayList<Persons> PersonInputReader(String file) throws FileNotFoundException{
	Scanner sc = new Scanner(new File(file));
	Persons e = null;
	sc.nextLine();
	while(sc.hasNextLine()){
		String line = sc.nextLine();
		String[] tokens = line.split(";");
		String id = tokens[0].trim();
		String name = tokens[1];
		String[] fullName = name.split(",");
		String firstName = fullName[0];
		String lastName = fullName[1];
		String address = tokens[2];
		String[] fullAddress = address.split(",");
		String street = fullAddress[0].trim();
		String city= fullAddress[1].trim();
		String state= fullAddress[2].trim();
		String zip = fullAddress[3].trim();
		String country= fullAddress[4].trim();
		String email = "";
		String secondEmail = "";
		if(tokens.length == 4){
			email = tokens[3].trim();
			if(email.contains(",")){
					String[] emailTokens = email.split(",");
					email = emailTokens[0];
					secondEmail = emailTokens[1];
					}
			} 
		e = new Persons(id, firstName, lastName, email, secondEmail, name, address);
		persons.add(e);
		
		}
	sc.close();
	return persons;
	}
	
	//Constructs an array list for the customer file
	public static ArrayList<Customer> CustomerInputReader(String customerFile) throws FileNotFoundException {
			Scanner sc = new Scanner(new File(customerFile));
			Customer e = null;
			sc.nextLine();
			while(sc.hasNextLine()){
				String line = sc.nextLine();
				String[] tokens = line.split(";");
				String customerCode = tokens[0];
				String typeOfCustomer = tokens[1];
				String primaryContact = tokens[2];
				
				
				Persons customerPersonContact = null;
				for(Persons aPerson: persons){
					if(primaryContact.equals(aPerson.getId())){
						customerPersonContact = aPerson;
					}
				}
				String name = tokens[3];
				String address = tokens[4];
				e = new Customer(customerCode, typeOfCustomer, customerPersonContact, name, address);
				customer.add(e);
			}
			sc.close();
			return customer;
	}

	//Constructs a list for products
	public static ArrayList<Product> ProductInputReader(String productFile) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(productFile));
		Product e = null;
		sc.nextLine();
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			String[] tokens = line.split(";");
			String code = tokens[0];
			String type = tokens[1];
			String name = tokens[2];
			//If the product is type E it sets it up accordingly
			if(tokens[1].toLowerCase().equals("e")){
				String pricePerUnit = tokens[3];
				e = new Equipment(code, type, name, pricePerUnit);
			}
			
			//If the product is type S it sets it up accordingly
			else if(tokens[1].toLowerCase().equals("s")){
				String activationFee = tokens[3];
				String annualFee = tokens[4];
				e = new Service(code, type, name, activationFee, annualFee, "");
			}
		
			//If the product is type C it sets it up accordingly
			else if(tokens[1].toLowerCase().equals("c")){
				String technicianPersonCode = tokens[3];
				String hourlyFee = tokens[4];
				e = new Consultation(code, type, name, technicianPersonCode, hourlyFee);
			}	
			products.add(e);	
		}
		sc.close();
		return products;
	}
	
	
	//Reads in invoice files
	public static ArrayList<Invoice> InvoiceReader(String invoiceFile) throws FileNotFoundException {
		Scanner sc = new Scanner(new File(invoiceFile));
		Invoice e = null;
		sc.nextLine();
		while(sc.hasNextLine()){
			String line = sc.nextLine();
			String[] tokens = line.split(";");
			String invoiceCode = tokens[0];
			String customerCode = tokens[1];
			
			//loop through customer to match the code
			Customer customers = null;
			for(Customer aCustomer : customer) {
				if(customerCode.equals(aCustomer.getCustomerCode())){
					customers = aCustomer;
				}
			}
			String dateOfInvoice = tokens[2];
			String salesPersonCode = tokens[3];
			
			//loop through personlist to find person
			Persons person = null;
			for(Persons aPerson : persons){
				if(salesPersonCode.equals(aPerson.getId())){
					person = aPerson;
				}
			}
			
			String productList = tokens[4];
			
			ArrayList<Product> products2 = new ArrayList<Product>();
			ArrayList<String> productInformation = new ArrayList<String>();
			
			//Deliminates and sorts relevant info from the invoice data file
			String[]tokens2 = productList.split(",");
			for(int i = 0; i < tokens2.length; i++){
				String[] tokens3 = tokens2[i].split(":");
				for(Product aProduct : products){
					if(tokens3[0].equals(aProduct.getCode())){
						products2.add(aProduct);
						if(aProduct instanceof Service){
							((Service)aProduct).setDate(tokens3[1] + ":" + tokens3[2]);
						}
					}
					productInformation.add(tokens3[1]);
				}
			}
			
			e = new Invoice(invoiceCode, customers, dateOfInvoice, person, products2, productInformation);
			invoice.add(e);
		}
		sc.close();
		return invoice;
}
	//Prints the invoice with this god awful amount of print statements
	public void print() throws NumberFormatException, ParseException{
		 
		
		System.out.println("=================================");
		System.out.println("INVOICE DETAIL REPORTS");
		System.out.print("=================================" + "\n");
		System.out.printf("%-8s %-40s %-33s %-17s %-12s %-12s %s %n","Invoice","Customer","Salesperson","SubTotal","Fees","Taxes","Total");
		
		double subTotalTotal = 0.0;
		double taxesTotal = 0.0;
		double feesTotal = 0.0;
		double finalTotalOmitCompliance = 0.0;
		for(Invoice aInvoice : invoice){
			ArrayList<Product> product2 = aInvoice.getProducts(); 
			
			
			for(int i =0; i< product2.size();i++){
				Product aProduct= product2.get(i);
			
			
			if(aProduct instanceof Equipment){
				((Equipment)aProduct).setNumUnits(aInvoice.getProductInformation().get(i));
				((Equipment)aProduct).setTotalCost();
				double subTotal = ((Double.parseDouble(((Equipment)aProduct).getPricePerUnit()) * Double.parseDouble(aInvoice.getProductInformation().get(i))));
				double taxes = subTotal * .07;
				double fees = 0.0;
				subTotalTotal += subTotal;
				taxesTotal += taxes;
				feesTotal += fees;
				finalTotalOmitCompliance = subTotalTotal + taxesTotal + feesTotal;
			}
			if(aProduct instanceof Consultation){
				((Consultation)aProduct).setHours(aInvoice.getProductInformation().get(i));
				((Consultation)aProduct).setTotalCost();
				double subTotal = ((Consultation)aProduct).GetTotalCost() ;
				double taxes = subTotal * .0425;
				double fees = 150.00;
				subTotalTotal += subTotal;
				taxesTotal += taxes;
				feesTotal += fees;
				finalTotalOmitCompliance = subTotalTotal + taxesTotal + feesTotal;
			}
			if(aProduct instanceof Service){
				((Service)aProduct).setTotalCost();
				double subTotal = ((Service)aProduct).getTotalCost();
				double taxes = subTotal * .0425;
				double fees = Double.parseDouble(((Service)aProduct).getActivationFee()) ;
				subTotalTotal += subTotal;
				taxesTotal += taxes;
				feesTotal += fees;
				finalTotalOmitCompliance = subTotalTotal + taxesTotal + feesTotal;
				}
			}
			System.out.printf("%-8s %-40s %-30s  %9.2f %18.2f %9.2f %9.2f %n",aInvoice.getInvoiceCode(),aInvoice.getCustomer().getName(), aInvoice.getPerson().getName(),subTotalTotal,feesTotal,taxesTotal,finalTotalOmitCompliance);			
		}
		System.out.println("===================================================================================================================================");
		System.out.printf("%-6s %9.2f %18.2f %9.2f %9.2f %n %n","TOTAL", subTotalTotal,feesTotal,taxesTotal,finalTotalOmitCompliance);
		
		for(Invoice aInvoice : invoice){
			System.out.println("----------------------------------");
			System.out.println("Invoice " + aInvoice.getInvoiceCode());
			System.out.println("----------------------------------");
			System.out.println("Salesperson: " + aInvoice.getPerson().getName());
			System.out.println("Customer:");
			System.out.println("    " + aInvoice.getCustomer().getName() + " (" + aInvoice.getCustomer().getCustomerCode() + ")");
			
			double complianceFee = 0.0;
			if(aInvoice.getCustomer().getTypeOfCustomer().equals("R")){
				System.out.println("    (Residential)");
				complianceFee += 125.00;
			}
			if(aInvoice.getCustomer().getTypeOfCustomer().equals("B")){
				System.out.println("    (Buisness)");
			}
			
			
			System.out.println("    " + aInvoice.getCustomer().getPrimaryContact().getName());
			System.out.println("    " + aInvoice.getCustomer().getAddress());
			System.out.println("----------------------------------");
			System.out.printf("%-8s %-61s %7s %11s %12s %12s %n","Code","Item","SubTotal","Taxes","Fees","Total");
			
			
			
			ArrayList<Product> product2 = aInvoice.getProducts(); 
			 subTotalTotal = 0.0;
			 taxesTotal = 0.0;
			 feesTotal = 0.0;
			 finalTotalOmitCompliance = 0.0;
			
			for(int i =0; i< product2.size();i++){
			Product aProduct= product2.get(i);
			
			//Finds different instances of a product and does the calculations 
			if(aProduct instanceof Equipment){
				((Equipment)aProduct).setNumUnits(aInvoice.getProductInformation().get(i));
				((Equipment)aProduct).setTotalCost();
				double subTotal = ((Double.parseDouble(((Equipment)aProduct).getPricePerUnit()) * Double.parseDouble(aInvoice.getProductInformation().get(i))));
				double taxes = subTotal * .07;
				double fees = 0.0;
				System.out.printf("%-8s %-8s %-43s %-13.2f %-14.2f %-8.2f %n",aProduct.getCode(),aProduct.getName(),((Equipment)aProduct).formatting(),subTotal,taxes, 0.00);
				subTotalTotal += subTotal;
				taxesTotal += taxes;
				feesTotal += fees;
				finalTotalOmitCompliance = subTotalTotal + taxesTotal + feesTotal;
			}
			if(aProduct instanceof Consultation){
				((Consultation)aProduct).setHours(aInvoice.getProductInformation().get(i));
				((Consultation)aProduct).setTotalCost();
				double subTotal = ((Consultation)aProduct).GetTotalCost();
				double taxes = subTotal * .0425;
				double fees = 150.00;
				System.out.printf("%-8s %-8s %-38s %-13.2f %-14.2f %-8.2f %n",aProduct.getCode(),aProduct.getName(),((Consultation)aProduct).formatting(),subTotal,taxes,fees);
				subTotalTotal += subTotal;
				taxesTotal += taxes;
				feesTotal += fees;
				finalTotalOmitCompliance = subTotalTotal + taxesTotal + feesTotal;
			}
			if(aProduct instanceof Service){
				((Service)aProduct).setTotalCost();
				double subTotal = ((Service)aProduct).getTotalCost();
				double taxes = subTotal * .0425;
				double fees = Double.parseDouble(((Service)aProduct).getActivationFee());
				System.out.printf("%-8s %-8s %-38s %-13.2f %-14.2f %-8.2f %n",aProduct.getCode(),aProduct.getName(),((Service)aProduct).formatting(),subTotal,taxes,fees);
				subTotalTotal += subTotal;
				taxesTotal += taxes;
				feesTotal += fees;
				finalTotalOmitCompliance = subTotalTotal + taxesTotal + feesTotal;
			}
			}
			System.out.printf("%117s %n","===============================================");		
			System.out.printf("%-66s %12.2f %12.2f %12.2f %12.2f %n","SUB-TOTALS",subTotalTotal,taxesTotal,feesTotal,finalTotalOmitCompliance);
			System.out.printf("%-113s %-2.2f %n","COMPLIANCE FEE",complianceFee);
			System.out.printf("%-109s %2.2f %n","FINAL TOTAL",(finalTotalOmitCompliance + complianceFee));
			System.out.println("\n");
			                 
			
			

			
			
		}
				
				
		
	}
	
	
	
	

}
