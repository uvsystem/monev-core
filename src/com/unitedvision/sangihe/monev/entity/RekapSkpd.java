package com.unitedvision.sangihe.monev.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "skpd")
public class RekapSkpd extends AbstractSkpd {

	private Long totalAnggaran;
	private Long totalRealisasiAnggaran;
	private Long totalRealisasiFisik;
	private Long jumlahKegiatan;

	/**
	 * Return total anggaran seluruh kegiatan SKPD.
	 * @return total anggaran.
	 */
	@Column(name = "total_anggaran")
	public Long getTotalAnggaran() {
		return totalAnggaran;
	}

	/**
	 * Atur total anggaran seluruh kegiatan SKPD.<br />
	 * Hanya digunakan oleh Jpa.
	 * @param totalAnggaran
	 */
	public void setTotalAnggaran(Long totalAnggaran) {
		this.totalAnggaran = totalAnggaran;
	}

	/**
	 * Return total realisasi anggaran seluruh kegiatan SKPD.
	 * @return total realisasi anggaran.
	 */
	@Column(name = "total_realisasi_anggaran")
	public Long getTotalRealisasiAnggaran() {
		return totalRealisasiAnggaran;
	}

	/**
	 * Atur total realisasi anggaran seluruh kegiatan SKPD.
	 * @param totalRealisasiAnggaran
	 */
	public void setTotalRealisasiAnggaran(Long totalRealisasiAnggaran) {
		this.totalRealisasiAnggaran = totalRealisasiAnggaran;
	}

	/**
	 * Return total realisasi fisik seluruh kegiatan SKPD.
	 * @return total realisasi fisik
	 */
	@Column(name = "total_realisasi_fisik")
	public Long getTotalRealisasiFisik() {
		return totalRealisasiFisik;
	}

	/**
	 * Atur total realisasi fisik seluruh kegiatan SKPD.
	 * @param totalRealisasiFisik
	 */
	public void setTotalRealisasiFisik(Long totalRealisasiFisik) {
		this.totalRealisasiFisik = totalRealisasiFisik;
	}

	/**
	 * Return jumlah kegiatan SKPD.
	 * @return jumlah kegiatan.
	 */
	@Column(name = "jumlah_kegiatan")
	public Long getJumlahKegiatan() {
		return jumlahKegiatan;
	}

	/**
	 * Atur jumlah kegiatan SKPD.
	 * @param jumlahKegiatan
	 */
	public void setJumlahKegiatan(Long jumlahKegiatan) {
		this.jumlahKegiatan = jumlahKegiatan;
	}
}
