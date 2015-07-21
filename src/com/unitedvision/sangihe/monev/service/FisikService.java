package com.unitedvision.sangihe.monev.service;

import java.util.List;

import com.unitedvision.sangihe.monev.entity.Fisik;

public interface FisikService {
	
	Fisik simpan(Fisik fisik);
	
	Fisik realisasi(Long id, Integer persentaseRealisasi, List<String> daftarFoto);
	
	void hapus(Long id);
	
	Fisik get(Long id);
	
	List<Fisik> getByKegiatan(Long id);
	
	List<Fisik> getByProgram(Long id);

}
