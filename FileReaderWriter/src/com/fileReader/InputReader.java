package com.fileReader;
import java.awt.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

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
				e = new Service(code, type, name, activationFee, annualFee);
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
			
			ArrayList<Product> products = new ArrayList<Product>();
			ArrayList<String> productInformation = new ArrayList<String>();
			
			String[]tokens2 = productList.split(",");
			for(int i = 0; i < tokens2.length; i++){
				String[] tokens3 = tokens2[i].split(":");
				for(Product aProduct : products){
					if(tokens3[0].equals(aProduct.getCode())){
						products.add(aProduct);
					}
					productInformation.add(tokens3[1]);
				}
			}
			
			e = new Invoice(invoiceCode, customers, dateOfInvoice, person, products, productInformation);
			invoice.add(e);
		}
		sc.close();
		return invoice;
}
	
	public void print(){

		System.out.println("=================================");
		System.out.println("INVOICE DETAIL REPORTS");
		System.out.println("=================================" + "\n");
		
		for(Invoice aInvoice : invoice){
			System.out.println("----------------------------------");
			System.out.println("Invoice " + aInvoice.getInvoiceCode());
			System.out.println("----------------------------------");
			System.out.println("Salesperson: " + aInvoice.getPerson().getName());
			System.out.println("Customer:");
			System.out.println(aInvoice.getCustomer().getName() + " (" + aInvoice.getCustomer().getCustomerCode() + ")");
			System.out.println(aInvoice.getCustomer().getPrimaryContact().getName());
			System.out.println(aInvoice.getCustomer().getAddress());
			System.out.println("----------------------------------");
			System.out.println("Code  " + "Item");
			ArrayList<Product> product2 = aInvoice.getProducts();  
			for(Product aProduct : product2){
			System.out.println(aProduct.getName());
			}
			
			
			
			
			
			System.out.println("\n");
//			
//			Salesperson: Eccleston, Chris
//			Customer: 
//				Stark Industries (C002)
//				(Business)
//				McCoy, Sylvester
//				912 E Kirwin Ave
//				Salina, KS  67401  USA
//			----------------------------------
//			Code     Item                                                            SubTotal       Taxes        Fees       Total    
//			b29e     Satellite Reciever  (2 units at 2500.0/unit)                 $   5000.00 $    350.00 $      0.00
//			90fa     Long Distance Service  (181 days at $12000.0/yr)             $   5950.68 $    252.90 $   2000.00
//			                                                                      ===============================================
//			SUB-TOTALS                                                            $  10950.68 $    602.90 $   2000.00 $  13553.59
//			COMPLIANCE FEE                                                                                            $      0.00
//			FINAL TOTAL                                                                                               $  13553.59

			
			
		}
				
				
		
	}
	
	
	
	

}
