package com.unitedvision.sangihe.monev.service;

import java.time.Month;
import java.util.List;

import com.unitedvision.sangihe.monev.entity.Anggaran;

public interface AnggaranService {

	Anggaran simpan(Anggaran anggaran);
	
	Anggaran realisasi(Long id, Long jumlahRealisasi);
	
	void hapus(Long id);
	
	Anggaran get(Long id);
	
	Anggaran get(Long idKegiatan, Integer tahun, Month bulan);
	
	List<Anggaran> getByProgram(Long id);
	
	List<Anggaran> getByKegiatan(Long id);
}
