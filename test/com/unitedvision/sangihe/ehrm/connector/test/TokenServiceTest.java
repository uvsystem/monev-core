package com.unitedvision.sangihe.ehrm.connector.test;

import static org.junit.Assert.*;

import java.nio.charset.Charset;
import java.util.Base64;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.HttpHeaders;

import com.unitedvision.sangihe.configuration.test.UtilityService;
//import com.unitedvision.sangihe.monev.entity.UnitKerja;
import com.unitedvision.sangihe.monev.serviceagent.Service;
import com.unitedvision.sangihe.monev.serviceagent.ServiceException;
import com.unitedvision.sangihe.monev.serviceagent.ServiceImpl;
//import com.unitedvision.sangihe.monev.serviceagent.entity.Pegawai;
import com.unitedvision.sangihe.monev.serviceagent.entity.Token;
import com.unitedvision.sangihe.monev.serviceagent.entity.Token.StatusToken;

public class TokenServiceTest {

	private Service service;
	private UtilityService utilityService;
	
//	private UnitKerja unitKerja;
//	private Pegawai pegawai;
	private Token tokenSetup;
	private Token tokenCreated;

	public static HttpHeaders createHeaders() {
		return new HttpHeaders(){
			private static final long serialVersionUID = 1L;

			{
				String auth = "superuser:dkakunsi";
				byte[] encodedAuth = Base64.getEncoder().encode(auth.getBytes(Charset.forName("US-ASCII")));
				String authHeader = "Basic " + new String( encodedAuth );
				set( "Authorization", authHeader );
			}
		};
	}
	
	@Before
	public void setup() throws Exception {
		ServiceImpl serviceImpl = new ServiceImpl();
		service = serviceImpl;
		utilityService = serviceImpl;
		tokenSetup = service.create("090213016", "password");
	}
	
	@Test
	public void test_create() throws ServiceException {
		tokenCreated = service.create("090213016", "password");
		
		assertNotNull(tokenCreated);
		assertNotEquals(tokenCreated, tokenSetup);
		assertEquals(StatusToken.AKTIF, tokenCreated.getStatus());
	}
	
	@Test
	public void test_lock() throws ServiceException {
		service.lock(tokenSetup.getToken());
	}
	
	@Test
	public void test_get() throws ServiceException {
		Token token2 = service.get(tokenSetup.getToken());
		
		assertNotNull(token2);
		assertEquals(StatusToken.AKTIF, token2.getStatus());
	}
	
	@After
	public void destroy() throws Exception {
		utilityService.hapus(createHeaders());
//		utilityService.hapus(pegawai, createHeaders());
//		utilityService.hapus(unitKerja, createHeaders());
	}

}
