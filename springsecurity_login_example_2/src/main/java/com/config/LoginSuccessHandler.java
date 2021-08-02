package com.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import com.dto.MyCustomersDetails;
import com.service.ICustemerService;

@Component
public class LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	
	@Autowired
	ICustemerService service;
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws ServletException, IOException {
		
		
       MyCustomersDetails userDetails =(MyCustomersDetails)authentication.getPrincipal();
		

       if (userDetails.getRole("USER")) {
		  
    		response.sendRedirect("/springsecurity_login_example_2/hello");
    	   
	}
		
       if (userDetails.getRole("ADMIN")) {
    	   
    	   response.sendRedirect("/springsecurity_login_example_2/admin");
		
	}
		
		
		
		
		
		   
		 
		
	}
	
	
	

}
