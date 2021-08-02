package com.config;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.DefaultRedirectStrategy;
import org.springframework.security.web.RedirectStrategy;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import com.dto.AutoritiesDto;
import com.dto.CustomersDto;
import com.service.ICustemerService;
import com.service.MyCustomersDetailsServiceImpl;


@Component
public class CustomLoginFuliarHandler extends SimpleUrlAuthenticationFailureHandler {

	
	private static final int ATTEMP = 3;
	@Autowired
	PasswordEncoder encoder;
	
	@Autowired
	ICustemerService  SERVICE;
	
	@Override
	public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response,
			AuthenticationException exception) throws IOException, ServletException {
		
		
	
		String email = request.getParameter("customEmail");
		
        List<CustomersDto> findCustomersByEmail = SERVICE.getEmail(email) ;         
		
		if (!findCustomersByEmail.isEmpty()) {
			//ukoliko je true
			if (findCustomersByEmail.get(0).isAccount_non_locked()) {
				if (findCustomersByEmail.get(0).getFailed_attempt()< ATTEMP - 1) {
				SERVICE.updateFailed_attempt(findCustomersByEmail.get(0));
				}else {
					SERVICE.lockTime(findCustomersByEmail.get(0));
					exception =  new LockedException("Treci pogresan pokusaj logovanja,Vas nalog ce biti zakljucan na 15min");
				}   // ukoliko je Account_non_locked() false
				}else if (!findCustomersByEmail.get(0).isAccount_non_locked()) {
				       if (SERVICE.unlockTime(findCustomersByEmail.get(0))) {
							exception =  new LockedException("Vas nalog je otkljucan, unesite ponovo vas email i password");
	
					}
				}
			}
			
		  super.setDefaultFailureUrl("/myLogin?error");
		  super.onAuthenticationFailure(request, response, exception);
		
		}
		
		
		
	
		
		
		
	}
	
	
	
	
	
	
	
	
	


