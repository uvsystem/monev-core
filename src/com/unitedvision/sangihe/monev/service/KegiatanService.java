package com.unitedvision.sangihe.monev.service;

import java.util.List;

import com.unitedvision.sangihe.monev.entity.Kegiatan;
import com.unitedvision.sangihe.monev.entity.RekapKegiatan;
import com.unitedvision.sangihe.monev.entity.Skpd;
import com.unitedvision.sangihe.monev.exception.ApplicationException;
import com.unitedvision.sangihe.monev.exception.EntityNotExistsException;
import com.unitedvision.sangihe.monev.exception.WrongYearException;

/**
 * Layanan pemrosesan Kegiatan.
 * 
 * @author Deddy Christoper Kakunsi
 *
 */
public interface KegiatanService {

	/**
	 * Simpan atau update data kegiatan.
	 * @param kegiatan
	 * @return Kegiatan dengan data baru.
	 * @throws ApplicationException
	 */
	Kegiatan simpan(Kegiatan kegiatan) throws WrongYearException;

	/**
	 * Hapus data kegiatan.
	 * @param kegiatan
	 * @return true jika berhasil, selain itu tidak.
	 * @throws ApplicationException
	 */
	boolean hapus(Kegiatan kegiatan);

	/**
	 * Ambil data Kegiatan berdasarkan id.
	 * @param id
	 * @return data Kegiatan
	 * @throws ApplicationException
	 */
	Kegiatan get(int id) throws EntityNotExistsException;

	/**
	 * Ambil data kegiatan berdasarkan Skpd.
	 * @param skpd
	 * @return data Kegiatan.
	 * @throws ApplicationException
	 */
	List<Kegiatan> get(Skpd skpd) throws EntityNotExistsException;

	/**
	 * Ambil semua data kegiatan.
	 * @return data kegiatan.
	 * @throws ApplicationException
	 */
	List<Kegiatan> get() throws EntityNotExistsException;

	/**
	 * Total semua anggaran SKPD pada tahun tertentu.
	 * @param skpd
	 * @param tahun
	 * @return total anggaran SKPD pada tahun tertentu.
	 * @throws ApplicationException
	 */
	long getTotalAnggaran(Skpd skpd, int tahun);

	/**
	 * Ambil data kegiatan berdasarkan SKPD.
	 * @param idSkpd id SKPD.
	 * @return data kegiatan.
	 * @throws EntityNotExistsException 
	 */
	List<Kegiatan> getBySkpd(Integer idSkpd) throws EntityNotExistsException;
	
	/**
	 * Rekap realisasi semua kegiatan, tanpa memperhitungkan SKPD.
	 * @return rekap realisasi kegiatan.
	 */
	List<RekapKegiatan> rekap();

	/**
	 * Rekap realisasi semua kegiatan SKPD.
	 * @param skpd
	 * @return rekap realisasi kegiatan.
	 */
	List<RekapKegiatan> rekap(Skpd skpd);

	/**
	 * Rekap realisasi seua kegiatan SKPD.
	 * @param idSkpd
	 * @return rekap realisasi kegiatan.
	 */
	List<RekapKegiatan> rekap(Integer idSkpd);

	/**
	 * Cari data kegiatan.
	 * @param keyword
	 * @return data kegiatan.
	 */
	List<Kegiatan> search(String keyword);
}
