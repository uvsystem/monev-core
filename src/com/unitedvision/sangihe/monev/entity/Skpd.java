package com.unitedvision.sangihe.monev.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

/**
 * Mapping table SKPD.
 * 
 * @author Deddy Christoper Kakunsi
 *
 */
@Entity
@Table(name = "skpd")
public class Skpd {

	private int id;
	private String kode;
	private String nama;
	
	private List<Kegiatan> listKegiatan;
	private List<Operator> listOperator;

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

	/**
	 * Return daftar kegiatan yang dilaksanakan. Daftar ini tidak akan dipublish menjadi JSON.
	 * @return daftar kegiatan.
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "skpd", fetch = FetchType.LAZY)
	public List<Kegiatan> getListKegiatan() {
		return listKegiatan;
	}

	/**
	 * Atur daftar kegiatan yang dilaksanakan.
	 * @param listKegiatan
	 */
	public void setListKegiatan(List<Kegiatan> listKegiatan) {
		this.listKegiatan = listKegiatan;
	}

	/**
	 * Return daftar operator.
	 * @return daftar operator.
	 */
	@JsonIgnore
	@OneToMany(mappedBy = "skpd", fetch = FetchType.LAZY)
	public List<Operator> getListOperator() {
		return listOperator;
	}

	/**
	 * Atur daftar operator.
	 * @param listOperator
	 */
	public void setListOperator(List<Operator> listOperator) {
		this.listOperator = listOperator;
	}
}
