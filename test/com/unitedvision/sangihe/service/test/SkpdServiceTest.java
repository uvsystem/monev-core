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
import com.unitedvision.sangihe.monev.entity.Skpd;
import com.unitedvision.sangihe.monev.exception.ApplicationException;
import com.unitedvision.sangihe.monev.exception.EntityNotExistsException;
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
	public void setup() throws ApplicationException {
		skpd = new Skpd();
		skpd.setKode("SKPD01");
		skpd.setNama("Nama SKPD 1");
		
		skpdService.simpan(skpd);
		
		count = skpdRepository.count();
	}
	
	@Test
	public void test_Insert() throws ApplicationException {
		Skpd skpd2 = new Skpd();
		skpd2.setKode("SKPD02");
		skpd2.setNama("Nama SKPD 2");
		
		skpdService.simpan(skpd2);

		assertEquals(count + 1, skpdRepository.count());
		assertNotEquals(0, skpd2.getId());
	}
	
	@Test(expected = PersistenceException.class)
	public void test_InsertDuplicate() throws ApplicationException {
		Skpd skpd2 = new Skpd();
		skpd2.setKode("SKPD01");
		skpd2.setNama("Nama SKPD 1");
		
		skpdService.simpan(skpd2);
	}
	
	@Test
	public void test_Delete() throws EntityNotExistsException {
		Skpd skpd2 = skpdService.get(skpd.getId());
		
		skpdService.hapus(skpd2);

		assertEquals(count - 1, skpdRepository.count());
	}
	
	@Test
	public void test_GetById() throws EntityNotExistsException {
		Skpd skpd2 = skpdService.get(skpd.getId());
		
		assertEquals(skpd, skpd2);
	}
	
	@Test
	public void test_GetAll() throws EntityNotExistsException {
		List<Skpd> list = skpdService.get();
		
		assertNotEquals(0, list.size());
	}
}
