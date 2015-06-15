package com.unitedvision.sangihe.monev.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

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
	@OneToMany(mappedBy = "kegiatan", fetch = FetchType.EAGER)
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
	
	@Transient
	public Long getRealisasiAnggaran() {
		long realisasiAnggaran = 0L;
		
		for (Realisasi realisasi : listRealisasi) {
			realisasiAnggaran += realisasi.getAnggaran();
		}
		
		return realisasiAnggaran;
	}
	
	public void setRealisasiAnggaran(Long realisasiAnggaran) {
		// Do Nothing
	}

	@Transient
	public Float getRealisasiFisik() {
		float realisasiFisik = 0L;
		
		for (Realisasi realisasi : listRealisasi) {
			realisasiFisik += realisasi.getFisik();
		}
		
		return realisasiFisik;
	}
	
	public void setRealisasiFisik(Long realisasiFisik) {
		// Do Nothing
	}
}
