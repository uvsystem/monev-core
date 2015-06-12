package com.unitedvision.sangihe.monev.security;

import org.springframework.stereotype.Component;

import com.unitedvision.sangihe.monev.entity.Operator;
import com.unitedvision.sangihe.monev.entity.Operator.Role;

/**
 * Class that provides authorization mechanism.
 * 
 * @author Deddy Christoper Kakunsi
 *
 */
@Component
public class AuthorizationProvider {
	
	/**
	 * Returns user's role depend on {@code Operator}.<br />
	 * User's role was defined in {@code Role} class.
	 * @param operator
	 * @return User's {@code Role}.
	 */
	public Role getUserRole(final Operator operator) {
		return operator.getRole();
	}

	/**
	 * Returns user's role in string format.
	 * @param role
	 * @return User's role in string format.
	 */
	public String getUserRoleStr(final Role role) {
		if (role == Role.ADMIN) {
			return "admin";
		} else if (role == Role.OPERATOR) {
			return "operator";
		} else {
			return "guest";
		}
	}

	/**
	 * Returns user's role in string format, based on {@code Operator}.
	 * @param operator
	 * @return user's role in string format.
	 */
	public String getUserRoleStr(final Operator operator) {
		return getUserRoleStr(getUserRole(operator));
	}
}
