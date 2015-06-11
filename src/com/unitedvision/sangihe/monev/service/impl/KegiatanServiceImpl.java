package com.unitedvision.sangihe.monev.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitedvision.sangihe.monev.entity.Kegiatan;
import com.unitedvision.sangihe.monev.entity.Skpd;
import com.unitedvision.sangihe.monev.exception.WrongYearException;
import com.unitedvision.sangihe.monev.repository.KegiatanRepository;
import com.unitedvision.sangihe.monev.service.KegiatanService;

@Service
@Transactional(readOnly = true)
public class KegiatanServiceImpl implements KegiatanService {

	@Autowired
	private KegiatanRepository kegiatanRepository;
	
	@Override
	@Transactional(readOnly = false)
	public Kegiatan simpan(Kegiatan kegiatan) throws WrongYearException {
		if (kegiatan.getAkhir() < kegiatan.getAwal())
			throw new WrongYearException("Tahun akhir sebelum tahun awal");
		
		return kegiatanRepository.save(kegiatan);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean hapus(Kegiatan kegiatan) {
		kegiatanRepository.delete(kegiatan);
		
		return true;
	}

	@Override
	public Kegiatan get(int id) {
		return kegiatanRepository.findOne(id);
	}

	@Override
	public List<Kegiatan> get(Skpd skpd) {
		return kegiatanRepository.findBySkpd(skpd);
	}

	@Override
	public List<Kegiatan> get() {
		return kegiatanRepository.findAll();
	}

	@Override
	public long getTotalAnggaran(Skpd skpd, int tahun) {
		return kegiatanRepository.summarizeAnggaran(skpd);
	}

}
