package com.unitedvision.sangihe.monev.exception;

/**
 * Exception yang spesifik pada realisasi.
 * 
 * @author Deddy Christoper Kakunsi
 *
 */
public class RealisasiException extends ApplicationException {

	private static final long serialVersionUID = 1L;

	public RealisasiException() {
		super();
	}

	/**
	 * Buat baru dengan pesan tertentu.
	 * @param arg0 pesan yang akan disampaikan.
	 */
	public RealisasiException(String arg0) {
		super(arg0);
	}
}
