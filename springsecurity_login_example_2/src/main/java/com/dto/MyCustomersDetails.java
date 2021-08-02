package com.dto;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;



public class MyCustomersDetails implements UserDetails {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private CustomersDto customersDto;
	private AutoritiesDto autoritiesDto;
	
	

	public MyCustomersDetails(CustomersDto customersDto, AutoritiesDto autoritiesDto) {
	     
	
		this.customersDto =customersDto; 
		this.autoritiesDto = autoritiesDto;
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
	

	
  	 List<SimpleGrantedAuthority> roles = Arrays.stream(autoritiesDto.getRoles().split(",")).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
		return roles;
	}

	@Override
	public String getPassword() {
		// TODO Auto-generated method stub
		return customersDto.getPassword();
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return customersDto.getEmail();
	}

	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return customersDto.isAccount_non_locked();
	}

	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return customersDto.isEnabled();
	}
	
	
public boolean getRole (String role) {
		
	
		if (autoritiesDto.getRoles().equals(role)) {
			
			return true;
		}
		else {
			return false;
		}
		
		
	}
}
	
	
