package com.unitedvision.sangihe.controller.test;

import static org.junit.Assert.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

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
import com.unitedvision.sangihe.monev.entity.Anggaran;
import com.unitedvision.sangihe.monev.entity.Kegiatan;
import com.unitedvision.sangihe.monev.entity.Program;
import com.unitedvision.sangihe.monev.entity.UnitKerja;
import com.unitedvision.sangihe.monev.entity.UnitKerja.TipeUnitKerja;
import com.unitedvision.sangihe.monev.repository.AnggaranRepository;
import com.unitedvision.sangihe.monev.repository.UnitKerjaRepository;
import com.unitedvision.sangihe.monev.service.AnggaranService;
import com.unitedvision.sangihe.monev.service.KegiatanService;
import com.unitedvision.sangihe.monev.service.ProgramService;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(classes = {TestConfig.class})
@Transactional
@TransactionConfiguration (defaultRollback = true)
public class AnggaranControllerTest {

	@Autowired
	private WebApplicationContext wac;
	private MockMvc mockMvc;

	@Autowired
	private AnggaranService anggaranService;
	@Autowired
	private KegiatanService kegiatanService;
	@Autowired
	private ProgramService programService; 
	@Autowired
	private UnitKerjaRepository unitKerjaRepository;
	@Autowired
	private AnggaranRepository anggaranRepository;
	
	private UnitKerja unitKerja;
	private Program program;
	private Kegiatan kegiatan;
	private Anggaran anggaran;
	
	private Long id;
	
	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac).build();
		
		unitKerja = new UnitKerja();
		unitKerja.setNama("Dinas Pariwisata");
		unitKerja.setSingkatan("DISPAR");
		unitKerja.setTipe(TipeUnitKerja.DINAS);
		unitKerjaRepository.save(unitKerja);
		
		program = new Program(unitKerja);
		program.setNama("Pembangunan Object Wisata");
		program.setTahunAwal(2015);
		program.setTahunAkhir(2015);
		programService.simpan(program);

		kegiatan = new Kegiatan(program);
		kegiatan.setNama("Pengembangan Pantai Pananuareng");
		kegiatan.setPaguAnggaran(100000000L);
		kegiatanService.simpan(kegiatan);

		anggaran = new Anggaran(kegiatan);
		anggaran.setTahun(2015);
		anggaran.setBulan(Month.JANUARY);
		anggaran.setRencana(10000000L);
		anggaranService.simpan(anggaran);
		
		assertEquals(1, anggaranRepository.count());
		
		id = anggaran.getId();
	}
	
	@Test
	public void test_simpan() throws Exception {
		this.mockMvc.perform(
				post(String.format("/anggaran/kegiatan/%d", kegiatan.getId()))
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"tahun\":\"2015\", "
						+ "\"bulan\":\"FEBRUARY\", "
						+ "\"rencana\":\"10000000\""
						+ "}")
			)
			.andExpect(jsonPath("$.message").value("Berhasil"))
			.andExpect(jsonPath("$.tipe").value("SUCCESS"));
	}
	
	@Test
	public void test_simpan_duplicate() throws Exception {
		this.mockMvc.perform(
				post(String.format("/anggaran/kegiatan/%d", kegiatan.getId()))
				.contentType(MediaType.APPLICATION_JSON)
				.content("{"
						+ "\"tahun\":\"2015\", "
						+ "\"bulan\":\"JANUARY\", "
						+ "\"rencana\":\"10000000\""
						+ "}")
			)
			.andExpect(jsonPath("$.message").value("Anggaran sudah terdaftar"))
			.andExpect(jsonPath("$.tipe").value("ERROR"));
	}
	
	@Test
	public void test_realisasi() throws Exception {
		this.mockMvc.perform(
				put(String.format("/anggaran/%d/realisasi/%d", id, 8000000))
				.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(jsonPath("$.message").value("Berhasil"))
			.andExpect(jsonPath("$.tipe").value("SUCCESS"));
	}
	
	@Test
	public void test_hapus() throws Exception {
		this.mockMvc.perform(
				delete(String.format("/anggaran/%d", id))
				.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(jsonPath("$.message").value("Berhasil"))
			.andExpect(jsonPath("$.tipe").value("SUCCESS"));
	}
	
	@Test
	public void test_get() throws Exception {
		this.mockMvc.perform(
				get(String.format("/anggaran/kegiatan/%d/tahun/%d/bulan/%s", kegiatan.getId(), 2015, "JANUARY"))
				.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(jsonPath("$.message").value("Berhasil"))
			.andExpect(jsonPath("$.tipe").value("ENTITY"));
	}
	
	@Test
	public void test_get_by_id() throws Exception {
		this.mockMvc.perform(
				get(String.format("/anggaran/%d", id))
				.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(jsonPath("$.message").value("Berhasil"))
			.andExpect(jsonPath("$.tipe").value("ENTITY"));
	}
	
	@Test
	public void test_get_by_program() throws Exception {
		this.mockMvc.perform(
				get(String.format("/anggaran/program/%d", program.getId()))
				.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(jsonPath("$.message").value("Berhasil"))
			.andExpect(jsonPath("$.tipe").value("LIST"));
	}
	
	@Test
	public void test_get_by_kegiatan() throws Exception {
		this.mockMvc.perform(
				get(String.format("/anggaran/kegiatan/%d", kegiatan.getId()))
				.contentType(MediaType.APPLICATION_JSON)
			)
			.andExpect(jsonPath("$.message").value("Berhasil"))
			.andExpect(jsonPath("$.tipe").value("LIST"));
	}
}
