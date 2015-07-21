package com.unitedvision.sangihe.monev.service;

import java.util.List;

import com.unitedvision.sangihe.monev.entity.Kegiatan;

public interface KegiatanService {

	Kegiatan simpan(Kegiatan kegiatan);

	void hapus(Kegiatan kegiatan);

	Kegiatan get(Integer id);

	List<Kegiatan> getByUnitKerja(Integer id);

	List<Kegiatan> get();

	List<Kegiatan> cari(String keyword);

	List<Kegiatan> getByProgram(Integer id);

}
