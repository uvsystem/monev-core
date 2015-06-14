package com.unitedvision.sangihe.monev.service.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitedvision.sangihe.monev.entity.Kegiatan;
import com.unitedvision.sangihe.monev.entity.RekapKegiatan;
import com.unitedvision.sangihe.monev.entity.Skpd;
import com.unitedvision.sangihe.monev.exception.EntityNotExistsException;
import com.unitedvision.sangihe.monev.exception.WrongYearException;
import com.unitedvision.sangihe.monev.repository.KegiatanRepository;
import com.unitedvision.sangihe.monev.repository.RekapKegiatanRepository;
import com.unitedvision.sangihe.monev.repository.SkpdRepository;
import com.unitedvision.sangihe.monev.service.KegiatanService;

@Service
@Transactional(readOnly = true)
public class KegiatanServiceImpl implements KegiatanService {

	@Autowired
	private KegiatanRepository kegiatanRepository;
	@Autowired
	private SkpdRepository skpdRepository;
	@Autowired
	private RekapKegiatanRepository rekapKegiatanRepository;
	
	@Override
	@Transactional(readOnly = false)
	public Kegiatan simpan(Kegiatan kegiatan) throws WrongYearException, PersistenceException {
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
	public Kegiatan get(int id) throws EntityNotExistsException {
		return kegiatanRepository.findOne(id);
	}

	@Override
	public List<Kegiatan> get(Skpd skpd) throws EntityNotExistsException {
		return kegiatanRepository.findBySkpd(skpd);
	}

	@Override
	public List<Kegiatan> get() throws EntityNotExistsException {
		return kegiatanRepository.findAll();
	}

	@Override
	public List<Kegiatan> getBySkpd(Integer idSkpd) throws EntityNotExistsException {
		Skpd skpd = skpdRepository.findOne(idSkpd);
		
		return get(skpd);
	}
	
	@Override
	public long getTotalAnggaran(Skpd skpd, int tahun) {
		return kegiatanRepository.summarizeAnggaran(skpd);
	}

	@Override
	public List<RekapKegiatan> rekap() {
		return rekapKegiatanRepository.rekap();
	}

	@Override
	public List<RekapKegiatan> rekap(Skpd skpd) {
		return rekapKegiatanRepository.rekap(skpd.getId());
	}

}
