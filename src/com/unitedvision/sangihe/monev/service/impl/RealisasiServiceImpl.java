package com.unitedvision.sangihe.monev.service.impl;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitedvision.sangihe.monev.entity.Kegiatan;
import com.unitedvision.sangihe.monev.entity.Realisasi;
import com.unitedvision.sangihe.monev.entity.Skpd;
import com.unitedvision.sangihe.monev.exception.AnggaranException;
import com.unitedvision.sangihe.monev.exception.EntityNotExistsException;
import com.unitedvision.sangihe.monev.exception.RealisasiException;
import com.unitedvision.sangihe.monev.exception.WrongYearException;
import com.unitedvision.sangihe.monev.repository.KegiatanRepository;
import com.unitedvision.sangihe.monev.repository.RealisasiRepository;
import com.unitedvision.sangihe.monev.service.RealisasiService;

@Service
@Transactional(readOnly = true)
public class RealisasiServiceImpl implements RealisasiService {

	@Autowired
	private RealisasiRepository realisasiRepository;
	@Autowired
	private KegiatanRepository KegiatanRepository;
	
	@Override
	@Transactional(readOnly = false)
	public Realisasi simpan(Realisasi realisasi) throws WrongYearException, RealisasiException, PersistenceException, AnggaranException {
		if (realisasi.getTahun() < realisasi.getKegiatan().getAwal()
			|| realisasi.getTahun() > realisasi.getKegiatan().getAkhir())
			throw new WrongYearException("Kesalahan: Tahun realisasi tidak sesuai dengan tahun kegiatan");
		
		if (realisasi.getAnggaran() <= 0)
			throw new AnggaranException("Kesalahan: Realisasi anggaran harus lebih dari 0");
		if (realisasi.getAnggaran() > realisasi.getKegiatan().getAnggaran())
			throw new AnggaranException("Kesalahan: Realisasi anggaran tidak boleh melebihi anggaran kegiatan");

		if (realisasi.getFisik() <= 0)
			throw new RealisasiException("Kesalahan: Realisasi fisik harus lebih dari 0%");
		if (realisasi.getFisik() > 100)
			throw new RealisasiException("Kesalahan: Realisasi fisik tidak boleh lebih dari 100%");

		long currentRealisasiAnggaran = realisasiRepository.summarizeAnggaran(realisasi.getKegiatan());
		if (currentRealisasiAnggaran + realisasi.getAnggaran() > realisasi.getKegiatan().getAnggaran())
			throw new RealisasiException("Kesalahan: Total realisasi anggaran melebihi anggaran kegiatan");
		
		long currentRealisasiFisik = realisasiRepository.summarizeFisik(realisasi.getKegiatan());
		if (currentRealisasiFisik + realisasi.getFisik() > 100)
			throw new RealisasiException("Kesalahan: Total realisasi fisik melebihi 100%");

		return realisasiRepository.save(realisasi);
	}

	@Override
	@Transactional(readOnly = false)
	public boolean hapus(Realisasi realisasi) {
		realisasiRepository.delete(realisasi);
		
		return true;
	}

	@Override
	public Realisasi get(int id) throws EntityNotExistsException {
		return realisasiRepository.findOne(id);
	}

	@Override
	public List<Realisasi> get(Kegiatan kegiatan) throws EntityNotExistsException {
		return realisasiRepository.findByKegiatan(kegiatan);
	}
	
	@Override
	public List<Realisasi> getByKegiatan(Integer idKegiatan) throws EntityNotExistsException {
		Kegiatan kegiatan = KegiatanRepository.findOne(idKegiatan);
		
		return get(kegiatan);
	}

	@Override
	public List<Realisasi> get() throws EntityNotExistsException {
		return realisasiRepository.findAll();
	}

	@Override
	public long getTotalRealisasiAnggaran(Kegiatan kegiatan) {
		return realisasiRepository.summarizeAnggaran(kegiatan);
	}

	@Override
	public long getTotalRealisasiAnggaran(Skpd skpd) {
		return realisasiRepository.summarizeAnggaran(skpd);
	}
}
