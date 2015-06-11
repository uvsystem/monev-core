package com.unitedvision.sangihe.monev.entity;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractSkpd {

	private int id;
	private String kode;
	private String nama;

	/**
	 * Return id SKPD.
	 * @return
	 */
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	/**
	 * Atur id SKPD.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Return kode SKPD.
	 * @return
	 */
	@Column(name = "kode")
	public String getKode() {
		return kode;
	}

	/**
	 * Atur kode SKPD.
	 * @param kode
	 */
	public void setKode(String kode) {
		this.kode = kode;
	}

	/**
	 * Return nama SKPD.
	 * @return
	 */
	@Column(name = "nama")
	public String getNama() {
		return nama;
	}

	/**
	 * Atur nama SKPD.
	 * @param nama
	 */
	public void setNama(String nama) {
		this.nama = nama;
	}

}
