package com.unitedvision.sangihe.monev.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Mapping tabel Kegiatan.
 * 
 * @author Deddy Christoper Kakunsi
 *
 */
@Entity
@Table(name = "kegiatan")
public class Kegiatan {

	private int id;
	private String nama;
	private long anggaran;
	private int awal;
	private int akhir;
	
	private Skpd skpd;
	
	private List<Realisasi> listRealisasi;

	/**
	 * Return id kegiatan.
	 * @return id kegiatan
	 */
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	/**
	 * Atur id kegiatan.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Return nama kegiatan.
	 * @return nama kegatan.
	 */
	@Column(name = "nama")
	public String getNama() {
		return nama;
	}

	/**
	 * Atur nama kegiatan.
	 * @param nama
	 */
	public void setNama(String nama) {
		this.nama = nama;
	}

	/**
	 * Return anggaran kegiatan.
	 * @return anggaran kegiatan.
	 */
	@Column(name = "anggaran")
	public long getAnggaran() {
		return anggaran;
	}

	/**
	 * Atur anggaran kegiatan.
	 * @param anggaran
	 */
	public void setAnggaran(long anggaran) {
		this.anggaran = anggaran;
	}

	/**
	 * Return awal kegiatan.
	 * @return awal kegiatan.
	 */
	@Column(name = "awal")
	public int getAwal() {
		return awal;
	}

	/**
	 * Atur awal kegiatan.
	 * @param awal
	 */
	public void setAwal(int awal) {
		this.awal = awal;
	}

	/**
	 * Return akhir kegiatan.
	 * @return akhir kegiatan.
	 */
	@Column(name = "akhir")
	public int getAkhir() {
		return akhir;
	}

	/**
	 * Atur akhir kegiatan.
	 * @param akhir
	 */
	public void setAkhir(int akhir) {
		this.akhir = akhir;
	}

	/**
	 * Return SKPD pelaksana kegiatan.
	 * @return SKPD pelaksana.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "skpd")
	public Skpd getSkpd() {
		return skpd;
	}

	/**
	 * Atur SKPD pelaksana kegiatan.
	 * @param skpd
	 */
	public void setSkpd(Skpd skpd) {
		this.skpd = skpd;
	}

	/**
	 * Return daftar realisasi kegiatan. Daftar ini tidak akan dipublish menjadi JSON.
	 * @return daftar realisasi.
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "kegiatan", fetch = FetchType.LAZY)
	public List<Realisasi> getListRealisasi() {
		return listRealisasi;
	}

	/**
	 * Atur daftar realisasi kegiatan.
	 * @param listRealisasi
	 */
	public void setListRealisasi(List<Realisasi> listRealisasi) {
		this.listRealisasi = listRealisasi;
	}
}
