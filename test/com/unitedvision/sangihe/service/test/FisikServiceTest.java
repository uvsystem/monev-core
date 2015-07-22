package com.unitedvision.sangihe.service.test;

import static org.junit.Assert.*;

import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.PersistenceException;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.unitedvision.sangihe.monev.configuration.ApplicationConfig;
import com.unitedvision.sangihe.monev.entity.Fisik;
import com.unitedvision.sangihe.monev.entity.Foto;
import com.unitedvision.sangihe.monev.entity.Kegiatan;
import com.unitedvision.sangihe.monev.entity.Program;
import com.unitedvision.sangihe.monev.entity.UnitKerja;
import com.unitedvision.sangihe.monev.entity.UnitKerja.TipeUnitKerja;
import com.unitedvision.sangihe.monev.repository.FisikRepository;
import com.unitedvision.sangihe.monev.repository.UnitKerjaRepository;
import com.unitedvision.sangihe.monev.service.FisikService;
import com.unitedvision.sangihe.monev.service.KegiatanService;
import com.unitedvision.sangihe.monev.service.ProgramService;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@Transactional
@TransactionConfiguration (defaultRollback = true)
public class FisikServiceTest {
	
	@Autowired
	private KegiatanService kegiatanService;
	@Autowired
	private ProgramService programService; 
	@Autowired
	private UnitKerjaRepository unitKerjaRepository;
	@Autowired
	private FisikService fisikService;
	@Autowired
	private FisikRepository fisikRepository;
	
	private UnitKerja unitKerja;
	private Program program;
	private Kegiatan kegiatan;
	private Fisik fisik;
	
	private Long id;

	@Before
	public void setup() {
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

		fisik = new Fisik(kegiatan);
		fisik.setTahun(2015);
		fisik.setBulan(Month.JANUARY);
		fisik.setRealisasi(8);
		fisikService.simpan(fisik);
		
		assertEquals(1, fisikRepository.count());
		
		id = fisik.getId();
	}
	
	@Test(expected = PersistenceException.class)
	public void test_simpan_duplicate() {
		fisik = new Fisik(kegiatan);
		fisik.setTahun(2015);
		fisik.setBulan(Month.JANUARY);
		fisikService.simpan(fisik);
	}
	
	@Test
	public void test_tambah_foto() {
		Fisik fisik = fisikService.tambahFoto(id, "http://picasa.com/sangihe/01/001.jpg");
		
		assertNotNull(fisik);
		assertEquals(id, fisik.getId());
		assertNotNull(fisik.getDaftarFoto());
		assertEquals(1, fisik.getDaftarFoto().size());
	}
	
	@Test
	public void test_tambah_foto_list() {
		List<Foto> daftarFoto = new ArrayList<Foto>();
		daftarFoto.add(new Foto("http://picasa.com/sangihe/01/001.jpg"));
		daftarFoto.add(new Foto("http://picasa.com/sangihe/01/001.jpg"));
		
		Fisik fisik = fisikService.tambahFoto(id, daftarFoto);
		
		assertNotNull(fisik);
		assertEquals(id, fisik.getId());
		assertNotNull(fisik.getDaftarFoto());
		assertEquals(2, fisik.getDaftarFoto().size());
	}
	
	@Test
	public void test_hapus() {
		fisikService.hapus(id);
		
		assertEquals(0, fisikRepository.count());
	}
	
	@Test
	public void test_get() {
		Fisik fisik = fisikService.get(id);
		
		assertNotNull(fisik);
		assertEquals(this.fisik, fisik);
	}
	
	@Test
	public void test_get_by_program() {
		List<Fisik> list = fisikService.getByProgram(program.getId());
		
		assertNotNull(list);
		assertNotEquals(0, list.size());
	}
	
	@Test
	public void test_get_by_kegiatan() {
		List<Fisik> list = fisikService.getByProgram(kegiatan.getId());
		
		assertNotNull(list);
		assertNotEquals(0, list.size());
	}
}
