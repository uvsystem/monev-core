package com.unitedvision.sangihe.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.time.Month;

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
import com.unitedvision.sangihe.ehrm.connector.DateUtil;
import com.unitedvision.sangihe.ehrm.connector.Service;
import com.unitedvision.sangihe.ehrm.connector.ServiceException;
import com.unitedvision.sangihe.ehrm.connector.ServiceImpl;
import com.unitedvision.sangihe.ehrm.connector.UtilityService;
import com.unitedvision.sangihe.ehrm.connector.entity.Pegawai;
import com.unitedvision.sangihe.ehrm.connector.entity.Token;
import com.unitedvision.sangihe.ehrm.connector.entity.UnitKerja;
import com.unitedvision.sangihe.ehrm.connector.entity.UnitKerja.TipeUnitKerja;
import com.unitedvision.sangihe.ehrm.connector.test.TokenServiceTest;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfig.class})
public class TokenControllerTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Autowired
	private Service service;
	
	private UtilityService utilityService;

	private UnitKerja unitKerja;
	private Pegawai pegawai;
	private Token tokenSetup;
	
	@Before
	public void setup() throws ServiceException {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		utilityService = new ServiceImpl();

		unitKerja = new UnitKerja();
		unitKerja.setNama("Dinas Pendidikan");
		unitKerja.setSingkatan("DIKNAS");
		unitKerja.setTipe(TipeUnitKerja.DINAS);
		utilityService.tambah(unitKerja, TokenServiceTest.createHeaders());
		
		unitKerja = utilityService.getUnitKerja("DIKNAS", TokenServiceTest.createHeaders());
		
		pegawai = new Pegawai(unitKerja);
		pegawai.setNip("090213016");
		pegawai.setPassword("password");
		pegawai.setNama("Deddy Kakunsi");
		pegawai.setNik("7171070512910002");
		pegawai.setTanggalLahir(DateUtil.getDate(1991, Month.DECEMBER, 5));
		pegawai.setTelepon("082347643198");
		pegawai.setEmail("deddykakunsi@gmail.com");
		utilityService.tambah(pegawai, unitKerja.getId(), TokenServiceTest.createHeaders());

		try {
			tokenSetup = service.create("090213016");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public void test_create_token() throws Exception {
		this.mockMvc.perform(
				post(String.format("/token/%s", "090213016"))
				.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(jsonPath("$.message").value("Berhasil"))
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
			.andExpect(jsonPath("$.tipe").value("ENTITY"));
	}
	
	@After
	public void destroy() throws ServiceException {
		utilityService.hapus(TokenServiceTest.createHeaders());
		utilityService.hapus(pegawai, TokenServiceTest.createHeaders());
		utilityService.hapus(unitKerja, TokenServiceTest.createHeaders());
	}
}
