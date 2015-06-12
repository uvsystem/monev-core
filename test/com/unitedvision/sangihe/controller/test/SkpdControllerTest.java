package com.unitedvision.sangihe.controller.test;

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

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.unitedvision.sangihe.monev.configuration.WebConfig;
import com.unitedvision.sangihe.monev.entity.Skpd;
import com.unitedvision.sangihe.monev.exception.ApplicationException;
import com.unitedvision.sangihe.monev.service.SkpdService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
@Transactional
@TransactionConfiguration (defaultRollback = true)
public class SkpdControllerTest {

	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private SkpdService skpdService;
	
	private MockMvc mockMvc;
	private Skpd skpd;
	
	@Before
	public void setup() throws ApplicationException {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		
		skpd = new Skpd();
		skpd.setKode("SKPD1");
		skpd.setNama("Nama SKPD 1");
		
		skpdService.simpan(skpd);
	}
	
	@Test
	public void test_GetAll() throws Exception {
		this.mockMvc.perform(get("/skpd").contentType(MediaType.APPLICATION_JSON))
			.andExpect(jsonPath("$.message").value("Berhasil"))
			.andExpect(jsonPath("$.tipe").value("LIST"));
	}
	
	@Test
	public void test_GetByIdNotFound() throws Exception {
		this.mockMvc.perform(get("/skpd/0").contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.message").value("Data tidak ditemukan"))
		.andExpect(jsonPath("$.tipe").value("ERROR"));
	}
	
	@Test
	public void test_GetById() throws Exception {
		int id = skpd.getId();
		
		this.mockMvc.perform(get(String.format("/skpd/%d", id)).contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.message").value("Berhasil"))
		.andExpect(jsonPath("$.tipe").value("ENTITY"));
	}
	
	@Test
	public void test_Insert() throws Exception {
		this.mockMvc.perform(post("/skpd").contentType(MediaType.APPLICATION_JSON).content("{\"id\":\"0\", \"kode\":\"SKPD2\", \"nama\":\"Nama SKPD 2\"}"))
		.andExpect(jsonPath("$.message").value("Berhasil"))
		.andExpect(jsonPath("$.tipe").value("SUCCESS"));
	}
	
	@Test
	public void test_InsertDuplicateKode() throws Exception {
		this.mockMvc.perform(post("/skpd").contentType(MediaType.APPLICATION_JSON).content("{\"id\":\"0\", \"kode\":\"SKPD1\", \"nama\":\"Nama SKPD 2\"}"))
		.andExpect(jsonPath("$.message").value("Kode yang anda masukan sudah digunakan"))
		.andExpect(jsonPath("$.tipe").value("ERROR"));
	}
	
	@Test
	public void test_InsertDuplicateNama() throws Exception {
		this.mockMvc.perform(post("/skpd").contentType(MediaType.APPLICATION_JSON).content("{\"id\":\"0\", \"kode\":\"SKPD2\", \"nama\":\"Nama SKPD 1\"}"))
		.andExpect(jsonPath("$.message").value("Nama yang anda masukan sudah digunakan"))
		.andExpect(jsonPath("$.tipe").value("ERROR"));
	}
}
