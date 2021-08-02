package com.cotrollers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.dto.CustomersDto;


@Controller
public class LoginController {
	
	
	@ModelAttribute("customersDto")
	public CustomersDto getCustomers() {
		
		CustomersDto customersDto = new CustomersDto();
		return customersDto;
		
		
	}
	
	
	@RequestMapping(value = "/myLogin", method = RequestMethod.GET)
	public String login(Model model) {
		
		 if (!model.containsAttribute("customersDto")) {
				
			 model.addAttribute("customersDto", new CustomersDto());
		}
		
		return "login-page";
	}
	
	
	
	@RequestMapping(value = "/process-login", method = RequestMethod.POST)
	public String Processlogin(@ModelAttribute("customerDto")CustomersDto customersDto) {
		
		
		
		return "redirect:/hello";
	}
	
	
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(@ModelAttribute("customerDto")CustomersDto customersDto) {
		
		
		return "admin-page";
	}
	
	@RequestMapping("/unauthorized")
	public String unauthorizedPage() {
		
		
		
		return "unauthorized-page";
		
		
		
	}
	

}
