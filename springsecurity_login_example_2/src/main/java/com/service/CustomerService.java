package com.service;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.ICustomerDao;
import com.dto.AutoritiesDto;
import com.dto.CustomersDto;

@Service
public class CustomerService implements ICustemerService {
	
    @Autowired
    ICustomerDao icustomerDao;


	@Override
	public void insertIntoCustomers(CustomersDto customersDto) {
		
	icustomerDao.insertIntoCustomers(customersDto);	
	
	}


	@Override
	public int getcityId(String country) {
	
	int getcityId = icustomerDao.getcityId(country);
		return getcityId;
	}


	@Override
	public int getgenderId(String gender) {
		int getgenderId = icustomerDao.getgenderId(gender);
		return getgenderId;
	}


	@Override
	public int getuserId(String email) {
		int getuserId = icustomerDao.getuserId(email);
		return getuserId;
	}


	@Override
	public List<CustomersDto> findCustomersByEmail(String email) {
		List<CustomersDto> findCustomersByEmail = icustomerDao.findCustomersByEmail(email);
		return findCustomersByEmail;
	}


	@Override
	public List<AutoritiesDto> findAuthoritiesByEmail(String email) {
		List<AutoritiesDto> findAuthoritiesByEmail = icustomerDao.findAuthoritiesByEmail(email);
		return findAuthoritiesByEmail;
	}



	public boolean unlockTime(CustomersDto customersDto) {
		
		boolean lockTime = icustomerDao.unlockTime(customersDto);
		return lockTime;
	}


	@Override
	public void updateFailed_attempt(CustomersDto customersDto) {
		
		icustomerDao.updateFailed_attempt(customersDto);
		
		
		
	}


	@Override
	public void lockTime(CustomersDto customersDto) {
		
		icustomerDao.lockTime(customersDto);
		
	}


	@Override
	public List<CustomersDto> getEmail(String email) {
		List<CustomersDto> list = icustomerDao.getEmail(email);
		return list;
	}


	

	
	
	
	
	
	

}
