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
import com.unitedvision.sangihe.monev.entity.Operator;
import com.unitedvision.sangihe.monev.entity.Operator.Role;
import com.unitedvision.sangihe.monev.entity.Skpd;
import com.unitedvision.sangihe.monev.exception.CredentialException;
import com.unitedvision.sangihe.monev.exception.EntityNotExistsException;
import com.unitedvision.sangihe.monev.repository.OperatorRepository;
import com.unitedvision.sangihe.monev.service.OperatorService;
import com.unitedvision.sangihe.monev.service.SkpdService;

@RunWith (SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationConfig.class})
@Transactional
@TransactionConfiguration (defaultRollback = true)
public class OperatorServiceTest {

	@Autowired
	private OperatorService operatorService;
	@Autowired
	private OperatorRepository operatorRepository;
	
	@Autowired
	private SkpdService skpdService;
	
	private Operator operator;
	private Skpd skpd;
	private long count;
	
	@Before
	public void setup() throws CredentialException {
		skpd = new Skpd();
		skpd.setKode("skpd01");
		skpd.setNama("Nama SKPD 01");
		
		skpdService.simpan(skpd);
		
		operator = new Operator();
		operator.setSkpd(skpd);
		operator.setUsername("username");
		operator.setPassword("password");
		operator.setRole(Role.ADMIN);
		
		operatorService.simpan(operator);
		
		count = operatorRepository.count();
	}
	
	@Test
	public void test_Insert() throws CredentialException {
		Operator operator2 = new Operator();
		operator2.setSkpd(skpd);
		operator2.setUsername("username 2");
		operator2.setPassword("password");
		operator2.setRole(Role.ADMIN);
		
		operatorService.simpan(operator2);
		
		assertEquals(count + 1, operatorRepository.count());
	}
	
	@Test(expected = PersistenceException.class)
	public void test_InsertDuplicate() throws CredentialException {
		Operator operator2 = new Operator();
		operator2.setSkpd(skpd);
		operator2.setUsername("username");
		operator2.setPassword("password");
		operator2.setRole(Role.ADMIN);
		
		operatorService.simpan(operator2);
	}
	
	@Test
	public void test_Hapus() {
		operatorService.hapus(operator);
		
		assertEquals(count - 1, operatorRepository.count());
	}
	
	@Test
	public void test_GetById() throws EntityNotExistsException {
		Operator operator2 = operatorService.get(operator.getId());
		
		assertEquals(operator, operator2);
	}
	
	@Test
	public void test_GetByUsername() throws EntityNotExistsException {
		Operator operator2 = operatorService.get(operator.getUsername());
		
		assertEquals(operator, operator2);
	}
	
	@Test
	public void test_GetBySkpd() throws EntityNotExistsException {
		List<Operator> list = operatorService.get(skpd);
		
		assertNotEquals(count - 1, list.size());
	}
	
	@Test
	public void test_GetAll() throws EntityNotExistsException {
		List<Operator> list = operatorService.get();
		
		assertNotEquals(count - 1, list.size());
	}
}
