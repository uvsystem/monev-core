package com.unitedvision.sangihe.monev.security;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.unitedvision.sangihe.ehrm.connector.TokenService;
import com.unitedvision.sangihe.ehrm.connector.entity.Operator;
import com.unitedvision.sangihe.ehrm.connector.entity.Operator.Role;
import com.unitedvision.sangihe.ehrm.connector.entity.Pegawai;
import com.unitedvision.sangihe.ehrm.connector.entity.Token;
import com.unitedvision.sangihe.monev.configuration.ApplicationConfig;
import com.unitedvision.sangihe.monev.exception.UnauthenticatedAccessException;

/**
 * Custom Authentication Provider.
 * 
 * @author Deddy Christoper Kakunsi
 *
 */
@Service("authService")
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private TokenService tokenService;

	@Override
	public CustomUser loadUserByUsername(String username) throws UsernameNotFoundException {
		Operator operator;
		Token token;

		try {
			token = tokenService.get(username);
			operator = getOperator(token.getpegawai());
		} catch (Exception e) {
			throw new UsernameNotFoundException("");
		}
		
		return new CustomUser(operator.getUsername(), token.getToken(), operator, getAuthorities(operator.getRole()));
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
	
	/**
	 * Check daftar operator, apakah aplikasi Monitoring dan Evaluasi (MONEV) terdaftar. 
	 * Jika terdaftar, return object {@link Operator}. Jika tidak terdaftar throw {@link UnauthenticatedAccessException}
	 * @param pegawai object pegawai yang berhasil login.
	 * @return Object {@link Operator} untuk aplikasi MONEV.
	 * @throws UnauthenticatedAccessException tidak ada operator untuk aplikasi MONEV.
	 */
	private Operator getOperator(Pegawai pegawai) throws UnauthenticatedAccessException {
		List<Operator> daftarOperator = pegawai.getDaftarOperator();

		for (Operator operator : daftarOperator) {
			if (operator.getAplikasi().getKode().equals(ApplicationConfig.KODE_APLIKASI))
				return operator;
		}
		
		throw new UnauthenticatedAccessException("Aplikasi Kepegawaian tidak bisa anda akses");
	}
}
