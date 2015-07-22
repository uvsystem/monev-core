package com.unitedvision.sangihe.monev.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitedvision.sangihe.monev.entity.Kegiatan;
import com.unitedvision.sangihe.monev.repository.KegiatanRepository;

@Service
@Transactional(readOnly = true)
public class KegiatanServiceImpl implements KegiatanService {

	@Autowired
	private KegiatanRepository kegiatanRepository;
	
	@Override
	@Transactional(readOnly = false)
	public Kegiatan simpan(Kegiatan kegiatan) {
		return kegiatanRepository.save(kegiatan);
	}

	@Override
	@Transactional(readOnly = false)
	public void hapus(Long id) {
		kegiatanRepository.delete(id);
	}

	@Override
	public Kegiatan get(Long id) {
		return kegiatanRepository.findOne(id);
	}

	@Override
	public List<Kegiatan> get() {
		return kegiatanRepository.findAll();
	}

	@Override
	public List<Kegiatan> getByUnitKerja(Long id) {
		return kegiatanRepository.findByProgram_UnitKerja_Id(id);
	}

	@Override
	public List<Kegiatan> getByProgram(Long id) {
		return kegiatanRepository.findByProgram_Id(id);
	}

	@Override
	public List<Kegiatan> cari(String keyword) {
		return kegiatanRepository.findByNamaContaining(keyword);
	}

}
