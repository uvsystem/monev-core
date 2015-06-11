package com.unitedvision.sangihe.service.test;

import static org.junit.Assert.*;

import java.time.Month;
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
import com.unitedvision.sangihe.monev.entity.Kegiatan;
import com.unitedvision.sangihe.monev.entity.Realisasi;
import com.unitedvision.sangihe.monev.entity.Skpd;
import com.unitedvision.sangihe.monev.exception.AnggaranException;
import com.unitedvision.sangihe.monev.exception.RealisasiException;
import com.unitedvision.sangihe.monev.exception.WrongYearException;
import com.unitedvision.sangihe.monev.repository.RealisasiRepository;
import com.unitedvision.sangihe.monev.service.KegiatanService;
import com.unitedvision.sangihe.monev.service.RealisasiService;
import com.unitedvision.sangihe.monev.service.SkpdService;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@Transactional
@TransactionConfiguration (defaultRollback = true)
public class RealisasiServiceTest {

	@Autowired
	private RealisasiService realisasiService;
	@Autowired
	private RealisasiRepository realisasiRepository;
	
	@Autowired
	private KegiatanService kegiatanService;
	@Autowired
	private SkpdService skpdService;
	
	private Realisasi realisasi;
	private Skpd skpd;
	private Kegiatan kegiatan;
	private long count;
	
	@Before
	public void setup() throws WrongYearException, RealisasiException, AnggaranException {
		skpd = new Skpd();
		skpd.setKode("skpd01");
		skpd.setNama("Nama SKPD 01");
		
		skpdService.simpan(skpd);
		
		kegiatan = new Kegiatan();
		kegiatan.setSkpd(skpd);
		kegiatan.setNama("Nama Kegiatan 1");
		kegiatan.setAnggaran(100000000);
		kegiatan.setAwal(2015);
		kegiatan.setAkhir(2016);
		
		kegiatanService.simpan(kegiatan);

		realisasi = new Realisasi(kegiatan);
		realisasi.setTahun(2015);
		realisasi.setBulan(Month.JANUARY);
		realisasi.setAnggaran(10000000);
		realisasi.setFisik(10);
		
		realisasiService.simpan(realisasi);
		
		count = realisasiRepository.count();
	}
	
	@Test
	public void test_Insert() throws WrongYearException, RealisasiException, AnggaranException {
		realisasi = new Realisasi(kegiatan);
		realisasi.setTahun(2015);
		realisasi.setBulan(Month.FEBRUARY);
		realisasi.setAnggaran(10000000);
		realisasi.setFisik(10);
		
		realisasiService.simpan(realisasi);
		
		assertEquals(count + 1, realisasiRepository.count());
		assertNotEquals(0, realisasi.getId());
	}
	
	@Test(expected = PersistenceException.class)
	public void test_InsertDuplicate() throws WrongYearException, RealisasiException, AnggaranException {
		realisasi = new Realisasi(kegiatan);
		realisasi.setTahun(2015);
		realisasi.setBulan(Month.JANUARY);
		realisasi.setAnggaran(10000000);
		realisasi.setFisik(10);
		
		realisasiService.simpan(realisasi);
	}
	
	@Test(expected = WrongYearException.class)
	public void test_InsertYearNotSame() throws WrongYearException, RealisasiException, AnggaranException {
		realisasi = new Realisasi(kegiatan);
		realisasi.setTahun(2014);
		realisasi.setBulan(Month.JANUARY);
		realisasi.setAnggaran(10000000);
		realisasi.setFisik(10);
		
		realisasiService.simpan(realisasi);
	}
	
	@Test(expected = RealisasiException.class)
	public void test_InsertFisikMoreThanMax() throws AnggaranException, RealisasiException, WrongYearException {
		realisasi = new Realisasi(kegiatan);
		realisasi.setTahun(2015);
		realisasi.setBulan(Month.FEBRUARY);
		realisasi.setAnggaran(10000000);
		realisasi.setFisik(101);
		
		realisasiService.simpan(realisasi);
	}
	
	@Test(expected = RealisasiException.class)
	public void test_InsertFisikLessThanMin() throws AnggaranException, RealisasiException, WrongYearException {
		realisasi = new Realisasi(kegiatan);
		realisasi.setTahun(2015);
		realisasi.setBulan(Month.FEBRUARY);
		realisasi.setAnggaran(10000000);
		realisasi.setFisik(0);
		
		realisasiService.simpan(realisasi);
	}
	
	@Test(expected = AnggaranException.class)
	public void test_InsertAnggaranMoreThanMax() throws AnggaranException, RealisasiException, WrongYearException {
		realisasi = new Realisasi(kegiatan);
		realisasi.setTahun(2015);
		realisasi.setBulan(Month.FEBRUARY);
		realisasi.setAnggaran(120000000);
		realisasi.setFisik(10);
		
		realisasiService.simpan(realisasi);
	}
	
	@Test(expected = AnggaranException.class)
	public void test_InsertAnggaranLessThanMin() throws AnggaranException, RealisasiException, WrongYearException {
		realisasi = new Realisasi(kegiatan);
		realisasi.setTahun(2015);
		realisasi.setBulan(Month.FEBRUARY);
		realisasi.setAnggaran(0);
		realisasi.setFisik(10);
		
		realisasiService.simpan(realisasi);
	}
	
	@Test(expected = RealisasiException.class)
	public void test_InsertTotalAnggaranMoreThanMax() throws AnggaranException, RealisasiException, WrongYearException {
		realisasi = new Realisasi(kegiatan);
		realisasi.setTahun(2015);
		realisasi.setBulan(Month.FEBRUARY);
		realisasi.setAnggaran(99000000);
		realisasi.setFisik(10);
		
		realisasiService.simpan(realisasi);
	}
	
	@Test
	public void test_Hapus() {
		realisasiService.hapus(realisasi);
		
		assertEquals(count - 1, realisasiRepository.count());
	}
	
	@Test
	public void test_GetById() {
		Realisasi realisasi2 = realisasiService.get(realisasi.getId());
		
		assertEquals(realisasi, realisasi2);
	}
	
	@Test
	public void test_GetByKegiatan() {
		List<Realisasi> list = realisasiService.get(kegiatan);
		
		assertNotEquals(0, list.size());
	}
	
	@Test
	public void test_GetAll() {
		List<Realisasi> list = realisasiService.get();
		
		assertNotEquals(0, list.size());
	}
	
	@Test
	public void test_GetTotalRealisasiAnggaranBySkpd() {
		long total = realisasiService.getTotalRealisasiAnggaran(skpd);
		
		assertNotEquals(0, total);
	}
	
	@Test
	public void test_GetTotalRealisasiAnggaranByKegiatan() {
		long total = realisasiService.getTotalRealisasiAnggaran(kegiatan);
		
		assertNotEquals(0, total);
	}
}
