package com.cotrollers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dto.CustomersDto;


@Controller
public class HomeController {
	
	@ModelAttribute("customersDto")
	public CustomersDto getCustomers() {
		
		CustomersDto customersDto = new CustomersDto();
		return customersDto;
		
		
	}
	
	
	@RequestMapping("/home")
	public String homePage(@ModelAttribute("customerDto")CustomersDto customersDto) {
		
		return "home-page";
	}


}
