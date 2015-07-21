package com.unitedvision.sangihe.monev.service;

import java.util.List;

import com.unitedvision.sangihe.monev.entity.Program;

public interface ProgramService {

	Program simpan(Program program);
	
	void hapus(Long id);
	
	Program get(Long id);
	
	List<Program> get();
	
	List<Program> getByUnitKerja(Long id);
	
	List<Program> cari(String keyword);
}
