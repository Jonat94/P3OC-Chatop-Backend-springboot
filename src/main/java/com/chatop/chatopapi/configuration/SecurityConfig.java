package com.chatop.chatopapi.configuration;



import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.chatop.chatopapi.filter.JwtRequestFilter;
import com.chatop.chatopapi.service.ApiService;


@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private ApiService apiService;

	@Autowired
	private JwtRequestFilter jwtRequestFilter;

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//A expliciter, decodage du password bcrypt
		auth.userDetailsService(apiService).passwordEncoder(passwordEncoder());
	}

	@Override
	public void configure(HttpSecurity http) throws Exception {
		//Disable csrf
		http = http.csrf().disable();
		
		
		
		//Set session mangment to stateless
		http.sessionManagement()
		.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
		.and();
		
		// Set unauthorized requests exception handler
       http = http
            .exceptionHandling()
            .authenticationEntryPoint(
                (request, response, ex) -> {
                    response.sendError(
                        HttpServletResponse.SC_UNAUTHORIZED,
                        ex.getMessage()
                    );
                }
            ).and();
        
      //Set Permissions on end points 
       //Filter for 401 error instead of bad request on invalid token
      	/*	http.authorizeRequests()
      		.antMatchers("/api/auth/login").permitAll()
      		.antMatchers("/api/auth/register").permitAll()
      		.anyRequest().authenticated();
       */
        
       //filter for swagger 
       http.authorizeRequests()
 		.antMatchers("/api/auth/me").authenticated()
 		.antMatchers("/api/rentals","/api/rentals/*", "/api/messages" ).authenticated()
 		.anyRequest().permitAll();
		
		//add JWT token filter
		http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

	}

	@Override
	@Bean
	public AuthenticationManager authenticationManagerBean() throws Exception {
		return super.authenticationManagerBean();
	}
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	

}
