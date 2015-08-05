package com.unitedvision.sangihe.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.unitedvision.sangihe.configuration.test.TestConfig;
import com.unitedvision.sangihe.monev.serviceagent.Service;
import com.unitedvision.sangihe.monev.serviceagent.ServiceException;
import com.unitedvision.sangihe.monev.serviceagent.UtilityService;
import com.unitedvision.sangihe.monev.serviceagent.entity.Token;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfig.class})
public class TokenControllerTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Autowired
	private Service service;

	@Autowired
	private UtilityService utilityService;

//	private UnitKerja unitKerja;
//	private Pegawai pegawai;
	private Token tokenSetup;
	
	@Before
	public void setup() throws ServiceException {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();

		tokenSetup = service.create("090213016", "kakunsi");
	}

	@Test
	public void test_create_token() throws Exception {
		this.mockMvc.perform(
				post(String.format("/token/%s", "020213036"))
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"password\":\"password\" "
						+ "}")
			)
			.andExpect(jsonPath("$.message").value("Berhasil"))
			.andExpect(jsonPath("$.model.status").value("AKTIF"))
			.andExpect(jsonPath("$.tipe").value("ENTITY"));
	}
	
	@Test
	public void test_lock_token() throws Exception {
		this.mockMvc.perform(
				put(String.format("/token/%s", tokenSetup.getToken()))
				.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(jsonPath("$.message").value("Berhasil"))
			.andExpect(jsonPath("$.tipe").value("SUCCESS"));
	}
	
	@Test
	public void test_get_token() throws Exception {
		this.mockMvc.perform(
				get(String.format("/token/%s", tokenSetup.getToken()))
				.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(jsonPath("$.message").value("Berhasil"))
			.andExpect(jsonPath("$.model.status").value("AKTIF"))
			.andExpect(jsonPath("$.tipe").value("ENTITY"));
	}
	
	@After
	public void destroy() throws ServiceException {
//		utilityService.hapus(TokenServiceTest.createHeaders());
//		utilityService.hapus(pegawai, TokenServiceTest.createHeaders());
//		utilityService.hapus(unitKerja, TokenServiceTest.createHeaders());
	}
}
