package com.unitedvision.sangihe.monev.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unitedvision.sangihe.monev.entity.Operator;
import com.unitedvision.sangihe.monev.entity.Operator.Role;
import com.unitedvision.sangihe.monev.exception.EntityNotExistsException;
import com.unitedvision.sangihe.monev.service.OperatorService;

/**
 * Custom Authentication Provider.
 * 
 * @author Deddy Christoper Kakunsi
 *
 */
@Service("authService")
public class CustomUserDetailsService implements UserDetailsService {
	@Autowired
	private OperatorService operatorService;

	@Override
	public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
		try {
			Operator operator = operatorService.get(username);
			
			return new CustomUser(username, operator.getPassword(), operator, getAuthorities(operator.getRole()));
		} catch (EntityNotExistsException e) {
			throw new UsernameNotFoundException(e.getMessage());
		}
	}

	public static List<GrantedAuthority> getAuthorities(Role role) {
		List<GrantedAuthority> authList = new ArrayList<>();

		if (role.equals(Role.ADMIN)) {
			authList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			authList.add(new SimpleGrantedAuthority("ROLE_OPERATOR"));
		} else if (role.equals(Role.OPERATOR)) {
			authList.add(new SimpleGrantedAuthority("ROLE_OPERATOR"));
		}

		return authList;
	}
}
