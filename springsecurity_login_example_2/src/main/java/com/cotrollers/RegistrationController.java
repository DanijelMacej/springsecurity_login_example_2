package com.cotrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dto.CustomersDto;
import com.service.ICustemerService;
@SessionAttributes("customersDto")
@Controller
public class RegistrationController {
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	@Autowired
	private ICustemerService custumerService;
	
	@ModelAttribute("customersDto")
	public CustomersDto getCustomers() {
		
		CustomersDto customersDto = new CustomersDto();
		return customersDto;
		
		
	}

	
	@RequestMapping(value = "/registration" , method = RequestMethod.GET)
	public String registrationPage (@ModelAttribute("customersDto") CustomersDto customersDto ) {
		
		
		return"registration-page";
	}
	
	
	@RequestMapping(value = "/registrationProcess" , method = RequestMethod.POST)
	public String processPage (@ModelAttribute("customersDto") CustomersDto customersDto ) {
		
		String encode = passwordEncoder.encode(customersDto.getPassword());
		customersDto.setPassword(encode);
		custumerService.insertIntoCustomers(customersDto);
		
		return"redirect:/hello";
	}
	
	@RequestMapping(value = "/hello" , method = RequestMethod.GET)
	public String helloPage (@ModelAttribute("customersDto") CustomersDto customersDto ) {
		
	
		
		return"hello-page";
	}
	
}
