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
import com.unitedvision.sangihe.monev.entity.RekapSkpd;
import com.unitedvision.sangihe.monev.entity.Skpd;
import com.unitedvision.sangihe.monev.repository.SkpdRepository;
import com.unitedvision.sangihe.monev.service.SkpdService;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@Transactional
@TransactionConfiguration (defaultRollback = true)
public class SkpdServiceTest {

	@Autowired
	private SkpdService skpdService;
	@Autowired
	private SkpdRepository skpdRepository;

	private Skpd skpd;
	private long count;
	
	@Before
	public void setup() {
		skpd = new Skpd();
		skpd.setKode("SKPD01");
		skpd.setNama("Nama SKPD 1");
		
		skpdService.simpan(skpd);
		
		count = skpdRepository.count();
	}
	
	@Test
	public void test_Insert() {
		Skpd skpd2 = new Skpd();
		skpd2.setKode("SKPD02");
		skpd2.setNama("Nama SKPD 2");
		
		skpdService.simpan(skpd2);

		assertEquals(count + 1, skpdRepository.count());
		assertNotEquals(0, skpd2.getId());
	}
	
	@Test(expected = PersistenceException.class)
	public void test_InsertDuplicate() {
		Skpd skpd2 = new Skpd();
		skpd2.setKode("SKPD01");
		skpd2.setNama("Nama SKPD 1");
		
		skpdService.simpan(skpd2);
	}
	
	@Test
	public void test_Delete() {
		Skpd skpd2 = skpdService.get(skpd.getId());
		
		skpdService.hapus(skpd2);

		assertEquals(count - 1, skpdRepository.count());
	}
	
	@Test
	public void test_GetById() {
		Skpd skpd2 = skpdService.get(skpd.getId());
		
		assertEquals(skpd, skpd2);
	}
	
	@Test
	public void test_GetAll() {
		List<Skpd> list = skpdService.get();
		
		assertNotEquals(0, list.size());
	}
	
	@Test
	public void test_Rekap() {
		List<RekapSkpd> rekap = skpdService.rekap();
		
		assertNotEquals(0, rekap.size());
	}
}
