package com.service;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.dto.AutoritiesDto;
import com.dto.CustomersDto;
import com.dto.MyCustomersDetails;

@Service
public class MyCustomersDetailsServiceImpl implements UserDetailsService  {

	@Autowired
	ICustemerService iCustomersService;
	
	
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

		List<CustomersDto> findCustomersByEmail = iCustomersService.findCustomersByEmail(email);
		List<AutoritiesDto> findAuthoritiesByEmail = iCustomersService.findAuthoritiesByEmail(email);

		if (findCustomersByEmail.isEmpty()) {

			throw new BadCredentialsException("The customer dont exist");

		}

		System.out.println(findAuthoritiesByEmail.get(0).getRoles());

		return new MyCustomersDetails(findCustomersByEmail.get(0), findAuthoritiesByEmail.get(0));

	}

}
