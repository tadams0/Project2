package com.greenbank.data;

import java.util.Set;

import com.greenbank.beans.Customer;

public interface CustomerDAO {

	public void addCustomer(Customer customer);
	
	public Customer getCustomer(Customer cust);
	
	public Set<Customer> getCustomers();
	
	public void deleteCustomer(Customer customer);
	
	public void updateCustomer(Customer customer);
}
