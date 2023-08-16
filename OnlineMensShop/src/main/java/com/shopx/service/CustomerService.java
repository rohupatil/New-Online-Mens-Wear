package com.shopx.service;

import java.util.List;

import com.shopx.dto.SignUpDTO;
import com.shopx.entities.Customer;



public interface CustomerService {

	public Customer newCustomerRegistration(SignUpDTO newCustomer);
	
	public List<Customer> getAllCustomerDetails();
	
	public Customer getCustomerById(Long id);
}
