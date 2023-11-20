package com.chatop.chatopapi.filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.chatop.chatopapi.service.ApiService;
import com.chatop.chatopapi.util.JwtTokenUtil;

@Component
public class JwtRequestFilter extends OncePerRequestFilter {

	@Autowired
	private ApiService apiService;

	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {

		// Get authorization header and validate
		final String header = request.getHeader(HttpHeaders.AUTHORIZATION);
		if (header == null || header.isEmpty() || !header.startsWith("Bearer ")) {
			filterChain.doFilter(request, response);
			return;
		}

		// Get jwt token and validate
		String token = header.split(" ")[1].trim();
		if (!(jwtTokenUtil.validateTokenIntegrity(token))) {
			filterChain.doFilter(request, response);
			return;
		}
		
		  // Get user identity and set it on the spring security context
        UserDetails userDetails = apiService.loadUserByUsername(jwtTokenUtil.getUsernameFromToken(token));

        UsernamePasswordAuthenticationToken
            authentication = new UsernamePasswordAuthenticationToken(
                userDetails, null,
                userDetails == null ?
                    List.of() : userDetails.getAuthorities()
            );

        authentication.setDetails(
            new WebAuthenticationDetailsSource().buildDetails(request)
        );

        SecurityContextHolder.getContext().setAuthentication(authentication);
        filterChain.doFilter(request, response);
    }
}
	
