package com.unitedvision.sangihe.monev.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitedvision.sangihe.monev.entity.Operator;
import com.unitedvision.sangihe.monev.entity.Skpd;
import com.unitedvision.sangihe.monev.repository.OperatorRepository;
import com.unitedvision.sangihe.monev.service.OperatorService;

@Service
@Transactional(readOnly = true)
public class OperatorServiceImpl implements OperatorService {

	@Autowired
	private OperatorRepository operatorRepository;
	
	@Override
	@Transactional(readOnly = false)
	public Operator simpan(Operator operator) {
		return operatorRepository.save(operator);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean hapus(Operator operator) {
		operatorRepository.delete(operator);
		
		return true;
	}
	
	@Override
	public Operator get(int id) {
		return operatorRepository.findOne(id);
	}

	@Override
	public Operator get(String username) {
		return operatorRepository.findByUsername(username);
	}

	@Override
	public List<Operator> get(Skpd skpd) {
		return operatorRepository.findBySkpd(skpd);
	}

	@Override
	public List<Operator> get() {
		return operatorRepository.findAll();
	}
}
