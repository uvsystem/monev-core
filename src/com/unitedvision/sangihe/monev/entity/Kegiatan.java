package com.unitedvision.sangihe.monev.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
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
public class Kegiatan extends AbstractKegiatan {

	private List<Realisasi> listRealisasi;

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
	
	/**
	 * Tambah realisasi kegiatan.
	 * @param realisasi
	 */
	public void addRealisasi(Realisasi realisasi) {
		realisasi.setKegiatan(this);
		this.listRealisasi.add(realisasi);
	}
	
	/**
	 * Hapus realisasi kegiatan.
	 * @param realisasi
	 */
	public void removeRealisasi(Realisasi realisasi) {
		realisasi.setKegiatan(null);
		this.listRealisasi.remove(realisasi);
	}
}
