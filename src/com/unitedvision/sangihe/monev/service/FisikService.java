package com.unitedvision.sangihe.monev.service;

import java.util.List;

import com.unitedvision.sangihe.monev.entity.Fisik;
import com.unitedvision.sangihe.monev.entity.Foto;

public interface FisikService {
	
	Fisik simpan(Fisik fisik);
	
	Fisik tambahFoto(Long id, String lokasiFoto);
	
	Fisik tambahFoto(Long id, List<Foto> daftarFoto);
	
	void hapus(Long id);
	
	Fisik get(Long id);
	
	List<Fisik> getByKegiatan(Long id);
	
	List<Fisik> getByProgram(Long id);

}
