package com.unitedvision.sangihe.monev.service;

import java.util.List;

import com.unitedvision.sangihe.monev.entity.Kegiatan;
import com.unitedvision.sangihe.monev.entity.RekapKegiatan;
import com.unitedvision.sangihe.monev.entity.SubKegiatan;

public interface KegiatanService {

	Kegiatan simpan(Kegiatan kegiatan);

	Kegiatan simpan(Kegiatan kegiatan, Long idProgram);

	void tambahSubKegiatan(Long idKegiatan, SubKegiatan subKegiatan);

	void hapus(Long id);

	Kegiatan get(Long id);

	List<Kegiatan> get();

	List<Kegiatan> getByUnitKerja(Long id);

	List<Kegiatan> getByProgram(Long id);

	List<Kegiatan> cari(String keyword);

	List<RekapKegiatan> rekap(Long tahun);

	List<RekapKegiatan> rekap(Long tahun, String kode);

	List<RekapKegiatan> rekap(Long tahun, Long id);

	RekapKegiatan rekapKegiatan(Long id);

}
