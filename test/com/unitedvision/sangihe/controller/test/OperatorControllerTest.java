package com.unitedvision.sangihe.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.WebApplicationContext;

import com.unitedvision.sangihe.configuration.test.TestConfig;
import com.unitedvision.sangihe.monev.entity.Operator;
import com.unitedvision.sangihe.monev.entity.Operator.Role;
import com.unitedvision.sangihe.monev.entity.Skpd;
import com.unitedvision.sangihe.monev.exception.ApplicationException;
import com.unitedvision.sangihe.monev.service.OperatorService;
import com.unitedvision.sangihe.monev.service.SkpdService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
@TransactionConfiguration (defaultRollback = true)
public class OperatorControllerTest {

	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private SkpdService skpdService;
	@Autowired
	private OperatorService operatorService;
	
	private MockMvc mockMvc;
	private Skpd skpd;
	private Operator operator;
	
	@Before
	public void setup() throws ApplicationException {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		
		skpd = new Skpd();
		skpd.setKode("SKPD1");
		skpd.setNama("Nama SKPD 1");
		skpdService.simpan(skpd);

		operator = new Operator();
		operator.setSkpd(skpd);
		operator.setUsername("username");
		operator.setPassword("password");
		operator.setRole(Role.ADMIN);
		operatorService.simpan(operator);
	}
	
	@Test
	public void test_Login() throws Exception {
		this.mockMvc.perform(post("/operator/login/username").contentType(MediaType.APPLICATION_JSON)
			.param("password", "password"))
			.andExpect(jsonPath("$.tipe").value("ENTITY"))
			.andExpect(jsonPath("$.message").value("Berhasil"))
			.andExpect(jsonPath("$.model.role").value("ADMIN"));
	}
	
	@Test
	public void test_LoginFailed() throws Exception {
		this.mockMvc.perform(post("/operator/login/username").contentType(MediaType.APPLICATION_JSON)
				.param("password", "wrong password"))
				.andExpect(jsonPath("$.model.role").value("ADMIN"))
				.andExpect(jsonPath("$.tipe").value("ERROR"))
				.andExpect(jsonPath("$.message").value("Username atau password salah"));
	}
	
	@Test
	public void test_GetBySkpd() throws Exception {
		this.mockMvc.perform(get(String.format("/operator/skpd/%d", skpd.getId())).contentType(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.tipe").value("LIST"))
				.andExpect(jsonPath("$.message").value("Berhasil"));
	}
}
