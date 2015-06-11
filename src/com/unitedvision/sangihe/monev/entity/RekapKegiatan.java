package com.unitedvision.sangihe.monev.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "kegiatan")
public class RekapKegiatan extends AbstractKegiatan {

	private Long totalRealisasiAnggaran;
	private Long totalRealisasiFisik;

	/**
	 * Return total realisasi anggaran kegiatan.
	 * @return total realisasi anggaran.
	 */
	@Column(name = "total_realisasi_anggaran")
	public Long getTotalRealisasiAnggaran() {
		return totalRealisasiAnggaran;
	}

	/**
	 * Atur total realisasi anggaran.<br />
	 * Hanya digunakan Jpa.
	 * @param totalRealisasiAnggaran
	 */
	public void setTotalRealisasiAnggaran(Long totalRealisasiAnggaran) {
		this.totalRealisasiAnggaran = totalRealisasiAnggaran;
	}

	/**
	 * Return total realisasi fisik kegiatan.
	 * @return total realisasi fisik.
	 */
	@Column(name = "total_realisasi_fisik")
	public Long getTotalRealisasiFisik() {
		return totalRealisasiFisik;
	}

	/**
	 * Atur total realisasi fisik.<br />
	 * Hanya digunakan Jpa.
	 * @param totalRealisasiFisik
	 */
	public void setTotalRealisasiFisik(Long totalRealisasiFisik) {
		this.totalRealisasiFisik = totalRealisasiFisik;
	}
}
