package com.fileWriter;

	
	import java.io.File;
	import java.io.FileNotFoundException;
	import java.io.PrintWriter;
	import java.util.List;
	import com.thoughtworks.xstream.XStream;
import com.datacontainers.Customer;
import com.datacontainers.Persons;
import com.datacontainers.Product;
import com.google.gson.Gson;
	import com.google.gson.GsonBuilder;


public class Exporter {
	
		//File writes person list in json	
		public void jsonConverter(List<Persons> persons) {
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			File jsonOutput = new File("data/Persons.json");
			
			PrintWriter jsonPrintWriter = null;
			
			try {
				jsonPrintWriter = new PrintWriter(jsonOutput);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
			
			for(Persons aPerson : persons) {
				// Use toJson method to convert Person object into a String
				String personOutput = gson.toJson(aPerson); 
				jsonPrintWriter.write(personOutput + "\n");
			}
			
			jsonPrintWriter.close();
		}
		
		//File writes customer listi in json
		public void jsonConverterCustomer(List<Customer> customer) {
			
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			File jsonOutput = new File("data/Customer.json");
		
			PrintWriter jsonPrintWriter = null;
		
			try {
				jsonPrintWriter = new PrintWriter(jsonOutput);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
		
			for(Customer aCustomer : customer) {
				// Use toJson method to convert Person object into a String
				String personOutput = gson.toJson(aCustomer); 
				jsonPrintWriter.write(personOutput + "\n");
			}
		
			jsonPrintWriter.close();
		}
		
		//File writes product list in json
		public void jsonConverterProduct(List<Product> product) {
			
			
			Gson gson = new GsonBuilder().setPrettyPrinting().create();
			File jsonOutput = new File("data/Product.json");
		
			PrintWriter jsonPrintWriter = null;
		
			try {
				jsonPrintWriter = new PrintWriter(jsonOutput);
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} 
		
			for(Product aProduct : product) {
				// Use toJson method to convert Person object into a String
				String personOutput = gson.toJson(aProduct); 
				jsonPrintWriter.write(personOutput + "\n");
			}
		
			jsonPrintWriter.close();
		}
		
		
		
		
		
		//File writes person list in xml
		public void xmlConverter(List<Persons> persons) {
				XStream  xstream = new XStream();
				
		        File xmlOutput = new File("data/Persons.xml");
				
				PrintWriter xmlPrintWriter = null;
				try {
					xmlPrintWriter = new PrintWriter(xmlOutput);
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				
				xmlPrintWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
				
				xstream.alias("person", Persons.class); 
				for(Persons aPerson : persons) {
					// Use toXML method to convert Person object into a String
					String personOutput = xstream.toXML(aPerson);
					xmlPrintWriter.write(personOutput);
				}
				xmlPrintWriter.close();	
			}
	
		//File writes customer list in xml
	public void xmlCustomerConverter(List<Customer> customer) {
		XStream  xstream = new XStream();
	
		File xmlOutput = new File("data/Customer.xml");
	
		PrintWriter xmlPrintWriter = null;
		try {
			xmlPrintWriter = new PrintWriter(xmlOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
		xmlPrintWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		
		xstream.alias("person", Persons.class); 
		for(Customer aCustomer : customer) {
			// Use toXML method to convert Person object into a String
			String personOutput = xstream.toXML(aCustomer);
			xmlPrintWriter.write(personOutput);
		}
		xmlPrintWriter.close();	
		}
	
	
	//File writes the product list in xml
	public void xmlProductConverter(List<Product> product) {
		XStream  xstream = new XStream();
	
		File xmlOutput = new File("data/Product.xml");
	
		PrintWriter xmlPrintWriter = null;
		try {
			xmlPrintWriter = new PrintWriter(xmlOutput);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	
		xmlPrintWriter.write("<?xml version=\"1.0\" encoding=\"UTF-8\" ?>\n");
		
		xstream.alias("person", Persons.class); 
		for(Product aProduct : product) {
			// Use toXML method to convert Person object into a String
			String personOutput = xstream.toXML(aProduct);
			xmlPrintWriter.write(personOutput);
		}
		xmlPrintWriter.close();	
		}
	}






	

	



