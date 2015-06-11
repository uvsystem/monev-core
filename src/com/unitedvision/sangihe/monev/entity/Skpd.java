package com.unitedvision.sangihe.monev.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Skpd extends AbstractSkpd {
	
	private List<Kegiatan> listKegiatan;
	private List<Operator> listOperator;

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
	 * Tambah kegiatan SKPD.
	 * @param kegiatan
	 */
	public void addKegiatan(Kegiatan kegiatan) {
		kegiatan.setSkpd(this);
		this.listKegiatan.add(kegiatan);
	}

	/**
	 * Hapus kegiatan SKPD.
	 * @param kegiatan
	 */
	public void removeKegiatan(Kegiatan kegiatan) {
		kegiatan.setSkpd(null);
		this.listKegiatan.remove(kegiatan);
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
	
	/**
	 * Tambah operator pada SKPD.
	 * @param operator
	 */
	public void addOperator(Operator operator) {
		operator.setSkpd(this);
		this.listOperator.add(operator);
	}
	
	/**
	 * Hapus operator pada SKPD.
	 * @param operator
	 */
	public void removeOperator(Operator operator) {
		operator.setSkpd(null);
		this.listOperator.remove(operator);
	}
}
