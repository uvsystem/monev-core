package com.unitedvision.sangihe.controller.test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.time.Month;

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
import com.unitedvision.sangihe.monev.entity.Kegiatan;
import com.unitedvision.sangihe.monev.entity.Realisasi;
import com.unitedvision.sangihe.monev.entity.Skpd;
import com.unitedvision.sangihe.monev.exception.ApplicationException;
import com.unitedvision.sangihe.monev.service.KegiatanService;
import com.unitedvision.sangihe.monev.service.RealisasiService;
import com.unitedvision.sangihe.monev.service.SkpdService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
@TransactionConfiguration (defaultRollback = true)
public class RealisasiControllerTest {

	@Autowired
	private WebApplicationContext wac;
	@Autowired
	private RealisasiService realisasiService;
	@Autowired
	private SkpdService skpdService;
	@Autowired
	private KegiatanService kegiatanService;
	
	private MockMvc mockMvc;
	private Skpd skpd;
	private Kegiatan kegiatan;
	private Realisasi realisasi;
	
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
		
		realisasi = new Realisasi(kegiatan);
		realisasi.setTahun(2015);
		realisasi.setBulan(Month.JANUARY);
		realisasi.setAnggaran(10000000);
		realisasi.setFisik(5);
		
		realisasiService.simpan(realisasi);
	}
	
	@Test
	public void test_GetAll() throws Exception {
		this.mockMvc.perform(get("/realisasi").contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.tipe").value("LIST"))
		.andExpect(jsonPath("$.message").value("Berhasil"));
	}
	
	@Test
	public void test_GetByKegiatan() throws Exception {
		this.mockMvc.perform(get(String.format("/realisasi/kegiatan/%d", kegiatan.getId())).contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.tipe").value("LIST"))
		.andExpect(jsonPath("$.message").value("Berhasil"));
	}
	
	@Test
	public void test_GetById() throws Exception {
		this.mockMvc.perform(get(String.format("/realisasi/%d", realisasi.getId())).contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.tipe").value("ENTITY"))
		.andExpect(jsonPath("$.message").value("Berhasil"));
	}
	
	@Test
	public void test_GetByIdNotFound() throws Exception {
		this.mockMvc.perform(get("/realisasi/0").contentType(MediaType.APPLICATION_JSON))
		.andExpect(jsonPath("$.tipe").value("ERROR"))
		.andExpect(jsonPath("$.message").value("Data tidak ditemukan"));
	}
	
	@Test
	public void test_Insert() {
		
	}
	
	@Test
	public void test_InsertDuplicate() {
		
	}
	
	@Test
	public void test_InsertYearNotSame() {
		
	}
	
	@Test
	public void test_InsertAnggaranLessThanMin() {
		
	}
	
	@Test
	public void test_InsertAnggaranMoreThanMax() {
		
	}
	
	@Test
	public void test_InsertFisikLessThanMin() {
		
	}
	
	@Test
	public void test_InsertFisikMoreThanMax() {
		
	}
}
