package com.unitedvision.sangihe.service.test;

import static org.junit.Assert.*;

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
import com.unitedvision.sangihe.monev.entity.RekapKegiatan;
import com.unitedvision.sangihe.monev.entity.Skpd;
import com.unitedvision.sangihe.monev.exception.WrongYearException;
import com.unitedvision.sangihe.monev.repository.KegiatanRepository;
import com.unitedvision.sangihe.monev.service.KegiatanService;
import com.unitedvision.sangihe.monev.service.SkpdService;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@Transactional
@TransactionConfiguration (defaultRollback = true)
public class KegiatanServiceTest {

	@Autowired
	private KegiatanService kegiatanService;
	@Autowired
	private KegiatanRepository kegiatanRepository;
	
	@Autowired
	private SkpdService skpdService;

	private Skpd skpd;
	private Kegiatan kegiatan;
	private long count;
	
	@Before
	public void setup() throws WrongYearException {
		skpd = new Skpd();
		skpd.setKode("skpd01");
		skpd.setNama("Nama SKPD 01");
		
		skpdService.simpan(skpd);
		
		kegiatan = new Kegiatan();
		kegiatan.setSkpd(skpd);
		kegiatan.setNama("Nama Kegiatan 1");
		kegiatan.setAnggaran(1000000);
		kegiatan.setAwal(2015);
		kegiatan.setAkhir(2016);
		
		kegiatanService.simpan(kegiatan);
		
		count = kegiatanRepository.count();
	}
	
	
	@Test
	public void test_Insert() throws WrongYearException {
		Kegiatan kegiatan2 = new Kegiatan();
		kegiatan2.setSkpd(skpd);
		kegiatan2.setNama("Nama Kegiatan 2");
		kegiatan2.setAnggaran(1000000);
		kegiatan2.setAwal(2015);
		kegiatan2.setAkhir(2016);
		
		kegiatanService.simpan(kegiatan2);
		
		assertEquals(count + 1, kegiatanRepository.count());
		assertNotEquals(0, kegiatan2.getId());
	}
	
	@Test(expected = PersistenceException.class)
	public void test_InsertDuplicate() throws WrongYearException {
		Kegiatan kegiatan2 = new Kegiatan();
		kegiatan2.setSkpd(skpd);
		kegiatan2.setNama("Nama Kegiatan 1");
		kegiatan2.setAnggaran(1000000);
		kegiatan2.setAwal(2015);
		kegiatan2.setAkhir(2016);
		
		kegiatanService.simpan(kegiatan2);
	}
	
	@Test(expected = WrongYearException.class)
	public void test_InsertAkhirBeforeAwal() throws WrongYearException {
		Kegiatan kegiatan2 = new Kegiatan();
		kegiatan2.setSkpd(skpd);
		kegiatan2.setNama("Nama Kegiatan 2");
		kegiatan2.setAnggaran(1000000);

		// This is main issue
		kegiatan2.setAwal(2015);
		kegiatan2.setAkhir(2014);
		
		kegiatanService.simpan(kegiatan2);
	}
	
	@Test
	public void test_Hapus() {
		Kegiatan kegiatan2 = kegiatanService.get(kegiatan.getId());

		kegiatanService.hapus(kegiatan2);

		assertEquals(count - 1, kegiatanRepository.count());
	}
	
	@Test
	public void test_getById() {
		Kegiatan kegiatan2 = kegiatanService.get(kegiatan.getId());
		
		assertEquals(kegiatan, kegiatan2);
	}
	
	@Test
	public void test_GetBySkpd() {
		List<Kegiatan> list = kegiatanService.get(skpd);
		
		assertNotEquals(0, list.size());
	}
	
	@Test
	public void test_GetAll() {
		List<Kegiatan> list = kegiatanService.get();
		
		assertNotEquals(0, list.size());
	}
	
	@Test
	public void test_GetTotalAnggaran() {
		long totalAnggaran = kegiatanService.getTotalAnggaran(skpd, 2015);
		
		assertNotEquals(0, totalAnggaran);
	}
	
	@Test
	public void test_RekapKegiatan() {
		List<RekapKegiatan> rekap = kegiatanService.rekap();
		
		for (RekapKegiatan rk : rekap) {
			System.out.println("XXX" + rk.getTotalRealisasiAnggaran());
			System.out.println(rk.getTotalRealisasiFisik());
		}
		
		assertNotEquals(0, rekap.size());
	}
	
	@Test
	public void test_GetKegiatanBySkpd() {
		List<RekapKegiatan> rekap = kegiatanService.rekap(skpd);
		
		for (RekapKegiatan rk : rekap) {
			System.out.println("XXX" + rk.getTotalRealisasiAnggaran());
			System.out.println(rk.getTotalRealisasiFisik());
		}
		
		assertNotEquals(0, rekap.size());
	}
}
