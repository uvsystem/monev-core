package com.unitedvision.sangihe.monev.service;

import java.util.List;

import javax.persistence.PersistenceException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitedvision.sangihe.monev.entity.Anggaran;
import com.unitedvision.sangihe.monev.entity.Fisik;
import com.unitedvision.sangihe.monev.entity.Kegiatan;
import com.unitedvision.sangihe.monev.entity.Program;
import com.unitedvision.sangihe.monev.entity.RekapKegiatan;
import com.unitedvision.sangihe.monev.entity.SubKegiatan;
import com.unitedvision.sangihe.monev.repository.AnggaranRepository;
import com.unitedvision.sangihe.monev.repository.FisikRepository;
import com.unitedvision.sangihe.monev.repository.KegiatanRepository;
import com.unitedvision.sangihe.monev.repository.ProgramRepository;
import com.unitedvision.sangihe.monev.repository.RekapKegiatanRepository;

@Service
@Transactional(readOnly = true)
public class KegiatanServiceImpl implements KegiatanService {

	@Autowired
	private KegiatanRepository kegiatanRepository;
	@Autowired
	private ProgramRepository programRepository;
	@Autowired
	private AnggaranRepository anggaranRepository;
	@Autowired
	private FisikRepository fisikRepository;
	@Autowired
	private RekapKegiatanRepository rekapKegiatanRepository;
	
	@Override
	@Transactional(readOnly = false)
	public Kegiatan simpan(Kegiatan kegiatan) {
		return kegiatanRepository.save(kegiatan);
	}
	
	@Override
	@Transactional(readOnly = false)
	public Kegiatan simpan(Kegiatan kegiatan, Long idProgram) {
		Program program = programRepository.findOne(idProgram);
		kegiatan.setProgram(program);
		
		return kegiatanRepository.save(kegiatan);
	}

	@Override
	@Transactional(readOnly = false)
	public void tambahSubKegiatan(Long idKegiatan, SubKegiatan subKegiatan) {
		Kegiatan parent = get(idKegiatan);
		parent.addSubKegiatan(subKegiatan);

		kegiatanRepository.save(subKegiatan);
	}
	
	@Override
	@Transactional(readOnly = false)
	public void hapus(Long id) {
		//kegiatanRepository.delete(id);
		kegiatanRepository.hapus(id);
	}

	@Override
	public Kegiatan get(Long id) {
		Kegiatan kegiatan = kegiatanRepository.findOne(id);
		
//		List<Anggaran> listAnggaran = anggaranRepository.findByKegiatan(kegiatan);
//		kegiatan.setDaftarAnggaran(listAnggaran);

//		List<Fisik> listFisik = fisikRepository.findByKegiatan(kegiatan);
//		kegiatan.setDaftarFisik(listFisik);
		
		return kegiatan;
	}

	@Override
	public List<Kegiatan> get() {
		return kegiatanRepository.findAll();
	}

	@Override
	public List<Kegiatan> getByUnitKerja(Long id) {
		List<Kegiatan> listKegiatan = kegiatanRepository.findByProgram_UnitKerja_Id(id);
		
		return listKegiatan;
	}

	@Override
	public List<Kegiatan> getByProgram(Long id) {
		return kegiatanRepository.findByProgram_Id(id);
	}

	@Override
	public List<Kegiatan> cari(String keyword) {
		return kegiatanRepository.findByNamaContaining(keyword);
	}

	@Override
	public List<RekapKegiatan> rekap(Long tahun) {
		return rekapKegiatanRepository.rekap(tahun);
	}

	@Override
	public List<RekapKegiatan> rekap(Long tahun, String kode) {
		return rekapKegiatanRepository.rekap(tahun, kode);
	}

	@Override
	public List<RekapKegiatan> rekap(Long tahun, Long id) {
		return rekapKegiatanRepository.rekap(tahun, id);
	}

	@Override
	public RekapKegiatan rekapKegiatan(Long id) {
		RekapKegiatan rekap = rekapKegiatanRepository.rekapKegiatan(id);
		
		try {
			List<Anggaran> daftarAnggaran = anggaranRepository.findByKegiatan_Program_Id(id);
			rekap.setDaftarAnggaran(daftarAnggaran);
		} catch (PersistenceException e) { }
		
		try {
			List<Fisik> daftarFisik = fisikRepository.findByKegiatan_Program_Id(id);
			rekap.setDaftarFisik(daftarFisik);
		} catch (PersistenceException e) { }
		
		return rekap;
	}

}
