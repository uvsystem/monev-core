package com.unitedvision.sangihe.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
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

import com.unitedvision.sangihe.monev.configuration.WebConfig;
import com.unitedvision.sangihe.monev.entity.Kegiatan;
import com.unitedvision.sangihe.monev.entity.Skpd;
import com.unitedvision.sangihe.monev.exception.ApplicationException;
import com.unitedvision.sangihe.monev.service.KegiatanService;
import com.unitedvision.sangihe.monev.service.SkpdService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {WebConfig.class})
@Transactional
@TransactionConfiguration (defaultRollback = true)
public class KegiatanControllerTest {

	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private SkpdService skpdService;
	@Autowired
	private KegiatanService kegiatanService;
	
	private MockMvc mockMvc;
	private Skpd skpd;
	private Kegiatan kegiatan;
	
	@Before
	public void setup() throws ApplicationException {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		
		skpd = new Skpd();
		skpd.setKode("SKPD1");
		skpd.setNama("Nama SKPD 1");
		skpdService.simpan(skpd);
		
		kegiatan = new Kegiatan();
		kegiatan.setSkpd(skpd);
		kegiatan.setNama("Kegiatan 1");
		kegiatan.setAnggaran(100000000);
		kegiatan.setAwal(2015);
		kegiatan.setAkhir(2016);
		kegiatanService.simpan(kegiatan);
		
	}

	@Test
	public void test_GetAll() throws Exception {
		this.mockMvc.perform(get("/kegiatan").contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.message").value("Berhasil"))
		.andExpect(jsonPath("$.tipe").value("LIST"));
	}
	
	@Test
	public void test_GetById() throws Exception {
		this.mockMvc.perform(get("/kegiatan").contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.message").value("Berhasil"))
		.andExpect(jsonPath("$.tipe").value("ENTITY"));
	}
	
	@Test
	public void test_GetByIdNotFound() throws Exception {
		this.mockMvc.perform(get("/kegiatan").contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.message").value("Berhasil"))
		.andExpect(jsonPath("$.tipe").value("ERROR"));
	}
	
	@Test
	public void test_Insert() {
		
	}
	
	@Test
	public void test_InsertDuplicate() {
		
	}
}
