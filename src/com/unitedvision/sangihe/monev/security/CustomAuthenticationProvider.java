package com.unitedvision.sangihe.monev.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.stereotype.Service;

@Service
public class CustomAuthenticationProvider implements AuthenticationProvider {
	@Autowired
	private CustomUserDetailsService userDetailService;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
	    String username = authentication.getName();
	    
        
        CustomUser user = userDetailService.loadUserByUsername(username);
 
       	System.out.println(String.format("LOG: CustomAuthenticationProvide.java: 35: Generated User: %s:%s", user.getUsername(), user.getPassword())); // LOG
        return new UsernamePasswordAuthenticationToken(user, user.getPassword(), user.getAuthorities());
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return true;
	}
}
