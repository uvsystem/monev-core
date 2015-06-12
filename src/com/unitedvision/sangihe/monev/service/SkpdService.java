package com.unitedvision.sangihe.monev.service;

import java.util.List;

import com.unitedvision.sangihe.monev.entity.RekapSkpd;
import com.unitedvision.sangihe.monev.entity.Skpd;
import com.unitedvision.sangihe.monev.exception.ApplicationException;
import com.unitedvision.sangihe.monev.exception.EntityNotExistsException;

/**
 * Layanan pemrosesan SKPD.
 * 
 * @author Deddy Christoper Kakunsi
 *
 */
public interface SkpdService {

	/**
	 * Simpan atau update data SKPD.
	 * @param skpd
	 * @return SKPD dengan data baru.
	 * @throws ApplicationException
	 */
	Skpd simpan(Skpd skpd);

	/**
	 * Hapus data SKPD.
	 * @param skpd
	 * @return true jika berhasil menghapus data, selain itu false.
	 * @throws ApplicationException
	 */
	boolean hapus(Skpd skpd);

	/**
	 * Ambil data Skpd menggunakan id.
	 * @param id
	 * @return Skpd menggunakan id.
	 * @throws ApplicationException
	 */
	Skpd get(int id) throws EntityNotExistsException;

	/**
	 * Ambil semua data Skpd.
	 * @return semua data Skpd.
	 * @throws ApplicationException
	 */
	List<Skpd> get() throws EntityNotExistsException;
	
	/**
	 * Rekap realisasi SKPD.
	 * @return rekap realisasi.
	 */
	List<RekapSkpd> rekap();
}
