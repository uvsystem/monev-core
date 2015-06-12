package com.unitedvision.sangihe.monev.service;

import java.util.List;

import com.unitedvision.sangihe.monev.entity.Kegiatan;
import com.unitedvision.sangihe.monev.entity.Realisasi;
import com.unitedvision.sangihe.monev.entity.Skpd;
import com.unitedvision.sangihe.monev.exception.EntityNotExistsException;
import com.unitedvision.sangihe.monev.exception.RealisasiException;
import com.unitedvision.sangihe.monev.exception.WrongYearException;

/**
 * Layanan pemrosesan Realisasi kegiatan. Realisasi berupa realisasi fisik dan anggaran.
 * 
 * @author Deddy Christoper Kakunsi
 *
 */
public interface RealisasiService {

	/**
	 * Simpan atau update data realisasi.
	 * @param realisasi
	 * @return Realisasi dengan data baru.
	 * @throws ApplicationException
	 */
	Realisasi simpan(Realisasi realisasi) throws WrongYearException, RealisasiException;

	/**
	 * Hapus data realisasi.
	 * @param realisasi
	 * @return true jika berhasil, selain itu false.
	 * @throws ApplicationException
	 */
	boolean hapus(Realisasi realisasi);

	/**
	 * Ambil data realisasi kegiatan berdasarkan id.
	 * @param id
	 * @return data Realisasi.
	 * @throws ApplicationException
	 */
	Realisasi get(int id) throws EntityNotExistsException;

	/**
	 * Ambil data realisasi kegiatan berdasarkan kegiatan.
	 * @param kegiatan
	 * @return data realisasi.
	 * @throws ApplicationException
	 */
	List<Realisasi> get(Kegiatan kegiatan) throws EntityNotExistsException;

	/**
	 * Ambil semua data realisasi.
	 * @return data realisasi.
	 * @throws ApplicationException
	 */
	List<Realisasi> get() throws EntityNotExistsException;
	
	/**
	 * Total realisasi anggaran kegiatan tertentu.
	 * @param kegiatan
	 * @return total realisasi anggaran.
	 */
	long getTotalRealisasiAnggaran(Kegiatan kegiatan);

	/**
	 * Total realisasi anggaran skpd tertentu.
	 * @param skpd
	 * @return total realisasi anggaran.
	 */
	long getTotalRealisasiAnggaran(Skpd skpd);

	/**
	 * Ambil data semua realisasi kegiatan.
	 * @param idKegiatan
	 * @return realisasi kegiatan.
	 */
	List<Realisasi> getByKegiatan(Integer idKegiatan);
}
