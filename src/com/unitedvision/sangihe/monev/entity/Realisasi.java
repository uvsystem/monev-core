package com.unitedvision.sangihe.monev.entity;

import java.time.Month;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.unitedvision.sangihe.monev.exception.RealisasiException;

/**
 * Mapping tabel realisasi.
 * 
 * @author Deddy Christoper Kakunsi
 *
 */
@Entity
@Table(name = "realisasi")
public class Realisasi {

	private int id;
	private int tahun;
	private Month bulan;
	private float fisik;
	private long anggaran;
	
	private Kegiatan kegiatan;

	/**
	 * Hanya digunakan oleh database.<br />
	 * @deprecated use constructor with Kegiatan parameter.
	 */
	@Deprecated
	public Realisasi() {
		super();
	}

	/**
	 * Buat realisasi kegiatan.
	 * @param kegiatan
	 */
	public Realisasi(Kegiatan kegiatan) {
		super();
		this.setKegiatan(kegiatan);
	}

	/**
	 * Return id realisasi.
	 * @return id realisasi.
	 */
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	/**
	 * Atur id realisasi.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Return tahun realisasi.
	 * @return tahun realisasi.
	 */
	@Column(name = "tahun")
	public int getTahun() {
		return tahun;
	}

	/**
	 * Atur tahun realisasi.
	 * @param tahun
	 */
	public void setTahun(int tahun) {
		this.tahun = tahun;
	}

	/**
	 * Return bulan realisasi.
	 * @return bulan realisasi.
	 */
	@Column(name = "bulan")
	public Month getBulan() {
		return bulan;
	}

	/**
	 * Atur bulan realisasi.
	 * @param bulan
	 */
	public void setBulan(Month bulan) {
		this.bulan = bulan;
	}

	/**
	 * Return realisasi fisik.
	 * @return realisasi fisik.
	 */
	@Column(name = "fisik")
	public float getFisik() {
		return fisik;
	}

	/**
	 * Atur realisasi fisik.
	 * @param fisik
	 */
	public void setFisik(float fisik) throws RealisasiException {
		this.fisik = fisik;
	}

	/**
	 * Return realisasi anggaran.
	 * @return realisasi anggaran.
	 */
	@Column(name = "anggaran")
	public long getAnggaran() {
		return anggaran;
	}

	/**
	 * Atur realisasi anggaran.
	 * @param anggaran
	 */
	public void setAnggaran(long anggaran) {
		this.anggaran = anggaran;
	}

	/**
	 * Return Kegiatan
	 * @return kegiatan
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "kegiatan")
	@JsonBackReference
	public Kegiatan getKegiatan() {
		return kegiatan;
	}

	/**
	 * Atur kegiatan.
	 * Note: Developer jangan langsung menggunakan method ini, tapi menggunakan constructor dengan parameter kegiatan.
	 * @param kegiatan
	 */
	public void setKegiatan(Kegiatan kegiatan) {
		this.kegiatan = kegiatan;
	}
}
