package com.unitedvision.sangihe.monev.service;

import java.time.Month;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.unitedvision.sangihe.monev.entity.Anggaran;
import com.unitedvision.sangihe.monev.repository.AnggaranRepository;

@Service
@Transactional(readOnly = true)
public class AnggaranServiceImpl implements AnggaranService {

	@Autowired
	private AnggaranRepository anggaranRepository;
	
	@Override
	@Transactional(readOnly = false)
	public Anggaran simpan(Anggaran anggaran) {
		return anggaranRepository.save(anggaran);
	}

	@Override
	@Transactional(readOnly = false)
	public Anggaran realisasi(Long id, Long jumlahRealisasi) {
		anggaranRepository.updateRealisasi(id, jumlahRealisasi);
		
		return get(id);
	}

	@Override
	@Transactional(readOnly = false)
	public void hapus(Long id) {
		anggaranRepository.delete(id);
	}

	@Override
	public Anggaran get(Long id) {
		return anggaranRepository.findOne(id);
	}

	@Override
	public Anggaran get(Long idKegiatan, Integer tahun, Month bulan) {
		return anggaranRepository.findByKegiatan_IdAndTahunAndBulan(idKegiatan, tahun, bulan);
	}

	@Override
	public List<Anggaran> getByProgram(Long id) {
		return anggaranRepository.findByKegiatan_Program_Id(id);
	}

	@Override
	public List<Anggaran> getByKegiatan(Long id) {
		return anggaranRepository.findByKegiatan_Id(id);
	}

}
