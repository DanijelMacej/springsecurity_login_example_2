package com.dao;

import java.util.List;

import com.dto.AutoritiesDto;
import com.dto.CustomersDto;

public interface ICustomerDao {
	
	
	
	public int getcityId(String country);
	
	 int getgenderId(String gender);
	 
	 int getuserId(String email);
	
	
	void insertIntoCustomers (CustomersDto customersDto);
	
	List<CustomersDto> findCustomersByEmail(String email);
	
	 List<AutoritiesDto>findAuthoritiesByEmail(String email);
	 
	 public boolean unlockTime(CustomersDto customersDto);
	 
	 void updateFailed_attempt(CustomersDto customersDto);
	 
	 public void lockTime(CustomersDto customersDto);
	 
	 List<CustomersDto> getEmail( String email);
	 
	 

}
