package com.unitedvision.sangihe.monev.service;

import java.util.List;

import com.unitedvision.sangihe.monev.entity.Operator;
import com.unitedvision.sangihe.monev.entity.Skpd;
import com.unitedvision.sangihe.monev.exception.ApplicationException;
import com.unitedvision.sangihe.monev.exception.EntityNotExistsException;

/**
 * Layanan pemrosesan Operator.
 * 
 * @author Deddy Christoper Kakunsi
 *
 */
public interface OperatorService {

	/**
	 * Simpan atau update data operator.
	 * @param operator
	 * @return Operator dengan data baru.
	 * @throws ApplicationException
	 */
	Operator simpan(Operator operator);

	/**
	 * Hapus data operator.
	 * @param operator
	 * @return true jika berhasil, selain itu false.
	 * @throws ApplicationException
	 */
	boolean hapus(Operator operator);

	/**
	 * Ambil data operator berdasarkan id.
	 * @param id
	 * @return data operator.
	 */
	Operator get(int id) throws EntityNotExistsException;
	
	/**
	 * Ambil data operator berdasarkan username.
	 * @param username
	 * @return data Operator.
	 * @throws ApplicationException
	 */
	Operator get(String username) throws EntityNotExistsException;

	/**
	 * Ambil data operator berdasarkan Skpd.
	 * @param skpd
	 * @return data operator.
	 * @throws ApplicationException
	 */
	List<Operator> get(Skpd skpd) throws EntityNotExistsException;

	/**
	 * Ambil semua data operator.
	 * @return data operator.
	 * @throws ApplicationException
	 */
	List<Operator> get() throws EntityNotExistsException;

	/**
	 * Ambil semua operator pada SKPD tertentu.
	 * @param idSkpd
	 * @return semua operator pada SKPD tertentu.
	 * @throws EntityNotExistsException
	 */
	List<Operator> getBySkpd(int idSkpd) throws EntityNotExistsException;
}
