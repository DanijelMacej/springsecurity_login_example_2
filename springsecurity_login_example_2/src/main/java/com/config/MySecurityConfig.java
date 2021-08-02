package com.config;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.LogoutSuccessHandler;

import com.service.MyCustomersDetailsServiceImpl;

@Configuration
@EnableWebSecurity(debug = true)
public class MySecurityConfig extends WebSecurityConfigurerAdapter {
	
	
	@Autowired
	CustomLoginFuliarHandler fuliarHandler;

	@Autowired
	LoginSuccessHandler loginSuccessHendler;
	
	@Autowired
	MyCustomersDetailsServiceImpl myCustomerDetailsServiceImpl;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	/*@Autowired
	private DaoAuthenticationProvider daoAuthenticationProvider;
	*/
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		auth
		.authenticationProvider(authenticationProvider());
	//	.userDetailsService(myCustomerDetailsServiceImpl).passwordEncoder(passwordEncoder);
		
		
		
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		
		http
		.authorizeRequests()
		.antMatchers("/registration").permitAll()
		.antMatchers("/home").permitAll()
		.antMatchers("/admin").hasAuthority("ADMIN")
		.and()
		.formLogin()
		.loginPage("/myLogin")
		.usernameParameter("customEmail")
		.passwordParameter("customPassword")
		.successHandler(loginSuccessHendler)
		.loginProcessingUrl("/process-login")
	//	.successForwardUrl("/process-login")
	    .failureHandler(fuliarHandler)
	//	.failureUrl("/myLogin?error")

		.and()
		.logout()
		.logoutSuccessHandler(new LogoutSuccessHandler() {
			
			@Override
			public void onLogoutSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication)
					throws IOException, ServletException {
				
				response.sendRedirect("/springsecurity_login_example_2/home");
				
			}
		})
		
		.and()
		.exceptionHandling().accessDeniedPage("/unauthorized");
	}
	
	
	@Bean
	DaoAuthenticationProvider authenticationProvider() {
		
		DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
		authenticationProvider.setPasswordEncoder(passwordEncoder);
		authenticationProvider.setUserDetailsService(myCustomerDetailsServiceImpl);
		
	
	
		return authenticationProvider;
	}

	
	
	
	

}
