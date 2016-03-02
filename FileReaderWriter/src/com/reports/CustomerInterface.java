package com.reports;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;

import com.fileReader.InputReader;
import com.fileWriter.Exporter;

public class CustomerInterface {
/***************************************************************************************
 * This sadness presented by Austin A Moore and Aurora Kenworthy
 * 
 * This program is supposed to read files and export them in xml, fml.
 * @param args
 * @throws FileNotFoundException
 */
	public static void main(String[] args) throws FileNotFoundException {
	
		
		String personfile = "data/Persons.dat";
		String customerFile = "data/Customers.dat";
		String productFile = "data/Products.dat";
		String invoiceFile = "data/Invoices.dat";
		InputReader inputRead = new InputReader();
		Exporter export = new Exporter();
		
		//Calls all the xml converter and stores the arrays 
		export.xmlConverter(inputRead.PersonInputReader(personfile));
		export.xmlCustomerConverter((inputRead.CustomerInputReader(customerFile)));
		export.xmlProductConverter(inputRead.ProductInputReader(productFile));
		inputRead.InvoiceReader(invoiceFile);
		inputRead.print();
		
		//Calls all the json converter methods and stores they arrays 
		export.jsonConverter((inputRead.PersonInputReader(personfile)));
		export.jsonConverterCustomer(inputRead.CustomerInputReader(customerFile));
		export.jsonConverterProduct(inputRead.ProductInputReader(productFile));
		
		
		 
		

	}

}
