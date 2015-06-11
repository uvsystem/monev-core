package com.unitedvision.sangihe.monev.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitedvision.sangihe.monev.entity.RekapSkpd;
import com.unitedvision.sangihe.monev.entity.Skpd;
import com.unitedvision.sangihe.monev.repository.RekapSkpdRepository;
import com.unitedvision.sangihe.monev.repository.SkpdRepository;
import com.unitedvision.sangihe.monev.service.SkpdService;

@Service
@Transactional(readOnly = true)
public class SkpdServiceImpl implements SkpdService {

	@Autowired
	private SkpdRepository skpdRepository;
	@Autowired
	private RekapSkpdRepository rekapSkpdRepository;
	
	@Override
	@Transactional(readOnly = false)
	public Skpd simpan(Skpd skpd) {
		return skpdRepository.save(skpd);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean hapus(Skpd skpd) {
		skpdRepository.delete(skpd);
		
		return true;
	}

	@Override
	public Skpd get(int id) {
		return skpdRepository.findOne(id);
	}

	@Override
	public List<Skpd> get() {
		return skpdRepository.findAll();
	}

	@Override
	public List<RekapSkpd> rekap() {
		return rekapSkpdRepository.rekap();
	}
}
