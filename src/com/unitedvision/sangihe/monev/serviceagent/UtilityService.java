package com.unitedvision.sangihe.monev.serviceagent;

import org.springframework.http.HttpHeaders;

import com.unitedvision.sangihe.monev.entity.UnitKerja;
import com.unitedvision.sangihe.monev.serviceagent.entity.Pegawai;

public interface UtilityService {

	void hapus(HttpHeaders headers) throws ServiceException;

	
	void tambah(Pegawai pegawai, Long idUnitKerja, HttpHeaders headers)throws ServiceException;
	
	void hapus(Pegawai pegawai, HttpHeaders headers) throws ServiceException;
	
	Pegawai getPegawai(String nip, HttpHeaders headers) throws ServiceException;

	
	void tambah(UnitKerja unitKerja, HttpHeaders headers) throws ServiceException;

	void hapus(UnitKerja unitKerja, HttpHeaders headers) throws ServiceException;
	
	UnitKerja getUnitKerja(String kode, HttpHeaders headers) throws ServiceException;

}
