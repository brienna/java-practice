package customerManager.io;

import java.util.*;
import java.io.*;
import java.nio.file.*;
import customerManager.business.Customer;

public class CustomerTextFile {
    private static final String FIELD_SEP = "\t";
    private static final Path customersPath = Paths.get("customers.txt");      
    private static final File customersFile = customersPath.toFile();      
    private static List<Customer> customers = getCustomers(); 
    
    // prevent instantiation of the class
    private CustomerTextFile() {}

    public static List<Customer> getCustomers() {
        // if the customers file has already been read, don't read it again
        if (customers != null)
            return customers;

        // Otherwise continue with reading the customers file
        customers = new ArrayList<>();
        
        // If customers file exists, 
        if (Files.exists(customersPath)) {  // prevent FileNotFoundException
        	// Open input stream
        	try (BufferedReader in = new BufferedReader(
        							 new FileReader(customersFile))) {
        		// Load array with Customer objects created from data in customers file
        		String line = in.readLine();
        		while (line != null) {  // prevent EOFException
        			String[] columns = line.split(FIELD_SEP);
        			Customer customer = new Customer(columns[0], columns[1], columns[2]);
        			System.out.println(customer);
        			customers.add(customer);
        			// Read the next line
        			line = in.readLine();
        		}
        	} catch (IOException e) {  // catches IOException
        		System.out.println("e");
        		return null;
        	}
        }
        
        return customers;        
    }

    public static Customer getCustomer(String email) {
        for (Customer c : customers) {
            if (c.getEmail().equals(email)) {
                return c;
            }
        }
        return null;
    }

    public static boolean addCustomer(Customer c) {
        customers.add(c);
        return saveCustomers();
    }

    public static boolean deleteCustomer(Customer c) {
        customers.remove(c);
        return saveCustomers();
    }

    public static boolean updateCustomer(Customer newCustomer) {
        // get the old customer and remove it
        Customer oldCustomer = getCustomer(newCustomer.getEmail());
        int i = customers.indexOf(oldCustomer);
        customers.remove(i);

        // add the updated customer
        customers.add(i, newCustomer);

        return saveCustomers();
    }

    private static boolean saveCustomers() {
        // save the Customer objects in the array list to the file
    	// open input stream for append operation
    	try (PrintWriter out = new PrintWriter(
    					  	   new BufferedWriter(
    					       new FileWriter(customersFile)))) {
    		// For each Customer object, save it to the file
    		for (Customer customer : customers) {
    			String record = customer.getFirstName() + FIELD_SEP + customer.getLastName() 
    				+ FIELD_SEP + customer.getEmail();
    			out.println(record);
    		}
    	} catch (IOException e) {
    		System.out.println(e);
    		return false;
    	}
    	
        return true;
    }
}