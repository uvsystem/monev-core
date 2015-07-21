package com.unitedvision.sangihe.monev.service;

import com.unitedvision.sangihe.monev.entity.rest.Token;

/**
 * Service Agent untuk mengambil token dari sistem eHRM.
 * 
 * @author Deddy Christoper Kakunsi
 *
 */
public interface TokenService {

	Token get(String tokenString);
	
	Token create(String nip);

	Token lock(String tokenString);
}
