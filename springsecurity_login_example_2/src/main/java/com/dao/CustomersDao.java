package com.dao;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.dto.AutoritiesDto;
import com.dto.CustomersDto;

@Repository
public class CustomersDao implements ICustomerDao {
	
	
	private static final int LOCK_TIME_DURACTION = 15 * 60 * 1000;

	
	@Autowired
	JdbcTemplate jdbcTemplate;
	
	public int getcityId(String country) {
		String query = "SELECT id_city FROM city WHERE city_name = ?";
		Integer queryForObject1 = jdbcTemplate.queryForObject(query, new Object[] {country}, int.class);
		return queryForObject1;
	}


	public int getgenderId(String gender) {
		String query = "SELECT id_gender FROM gender WHERE type_of_sex = ?";
		Integer queryForObject2 = jdbcTemplate.queryForObject(query, new Object [] {gender},int.class);
		return queryForObject2;
	}
	
	
	public int getuserId(String email) {
		String query = "SELECT id FROM customers WHERE email = ?";
		Integer queryForObject = jdbcTemplate.queryForObject(query, new Object[] {email}, int.class);
		return queryForObject;
	}
	

	
	
	
	
	
    public void insertIntoCustomers (CustomersDto customersDto) {
		
 	   customersDto.setEnabled(false);
 	   customersDto.setAccount_non_locked(true);
 	   customersDto.setFailed_attempt(0);
 	   
		String queryCustomers = "INSERT INTO customers VALUES (null,?,?,?,?,?,?,?,?,?,?)";
		Object []argsCustomers = {customersDto.getName(),customersDto.getPassword(),customersDto.getEmail(),
				getgenderId(customersDto.getGender()), getcityId(customersDto.getCity()),customersDto.isChack(),customersDto.isEnabled(),
			 customersDto.getFailed_attempt(),  customersDto.isAccount_non_locked(),customersDto.getLock_time()};
		
		jdbcTemplate.update(queryCustomers,argsCustomers);
		
		
		String quryRoles ="INSERT INTO authorities VALUES (null,?,?)";
		Object []argsRoles = {getuserId(customersDto.getEmail()), "USER"};	
		
		jdbcTemplate.update(quryRoles,argsRoles);
		
		
	}

	@Override
	public List<CustomersDto> findCustomersByEmail(String email) {
		String query = "SELECT email, password , 1 AS enabled, account_non_locked FROM customers WHERE email = ? ";
		List<CustomersDto> customersList = jdbcTemplate.query(query,
				new BeanPropertyRowMapper<CustomersDto>(CustomersDto.class), email);
		return customersList;
	}

	@Override
	public List<AutoritiesDto> findAuthoritiesByEmail(String email) {

		String query = "SELECT customers.email , authorities.roles FROM customers , authorities WHERE customers.email = ?"
				+ " AND customers.id = authorities.id_user";
		List<AutoritiesDto> authoritiesList = jdbcTemplate.query(query,
				new BeanPropertyRowMapper<AutoritiesDto>(AutoritiesDto.class), email);
		return authoritiesList;
	}
	
	
	
	public boolean unlockTime(CustomersDto customersDto) {
		
		long lockTimeMilisec = customersDto.getLock_time().getTime();
		long currentTimeMillis = System.currentTimeMillis();
		
		if (lockTimeMilisec + LOCK_TIME_DURACTION < currentTimeMillis ) {
			customersDto.setAccount_non_locked(true);
			customersDto.setLock_time(null);
			customersDto.setFailed_attempt(0);
			String query = "UPDATE customers SET failed_attempt = ?, account_non_locked = ? , lock_time = ? WHERE email = ?";
			Object [] args = {customersDto.getFailed_attempt(),customersDto.isAccount_non_locked(), customersDto.getLock_time(), customersDto.getEmail()};
			jdbcTemplate.update(query,args);
			return true;
			
		}
		
		   return false;
		
	}
	
	
	
	
	public void updateFailed_attempt(CustomersDto customersDto) {
		
		String query = "UPDATE customers SET failed_attempt = ? WHERE email = ?";
		Object []args = {getfailed_attempt(customersDto.getFailed_attempt()), customersDto.getEmail()};
		jdbcTemplate.update(query,args);
	}
	
	
	public int getfailed_attempt(int Failed_attempt) {
		
     int attempt = Failed_attempt + 1;
		
		return attempt;
	}

	
	
	public void lockTime(CustomersDto customersDto) {
		
		customersDto.setAccount_non_locked(false);
		customersDto.setLock_time(new Date());
		
		  String query = "UPDATE customers SET  account_non_locked = ?, lock_time = ?  where email = ?";
	 	   Object[]args = {customersDto.isAccount_non_locked(),customersDto.getLock_time(),customersDto.getEmail()};
	 	   jdbcTemplate.update(query,args);
		
	}
	
	
	public List<CustomersDto> getEmail(String email) {
		
		String query = "SELECT * FROM customers WHERE email = ?";
		List<CustomersDto> listaCustomers = jdbcTemplate.query(query, new BeanPropertyRowMapper<CustomersDto>(CustomersDto.class),email);
		return listaCustomers;
	}
	

	
	
	

}
