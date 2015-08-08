package com.unitedvision.sangihe.monev.entity;

import java.time.Month;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Transient;

@Entity
public class RekapKegiatan {

	private String namaUnitKerja;
	private String namaProgram;
	private String namaKegiatan;
	private Long paguAnggaran;
	private Long realisasiAnggaran;
	private Integer realisasiFisik;
	
	private List<Anggaran> daftarAnggaran;
	private List<Fisik> daftarFisik;
	
	public RekapKegiatan() {
		super();
	}

	@Column(name = "unit_kerja")
	public String getNamaUnitKerja() {
		return namaUnitKerja;
	}

	public void setNamaUnitKerja(String namaUnitKerja) {
		this.namaUnitKerja = namaUnitKerja;
	}

	@Column(name = "program")
	public String getNamaProgram() {
		return namaProgram;
	}

	public void setNamaProgram(String namaProgram) {
		this.namaProgram = namaProgram;
	}

	@Id
	@Column(name = "kegiatan")
	public String getNamaKegiatan() {
		return namaKegiatan;
	}

	public void setNamaKegiatan(String namaKegiatan) {
		this.namaKegiatan = namaKegiatan;
	}

	@Column(name = "pagu_anggaran")
	public Long getPaguAnggaran() {
		return paguAnggaran;
	}

	public void setPaguAnggaran(Long paguAnggaran) {
		this.paguAnggaran = paguAnggaran;
	}

	@Column(name = "realisasi_anggaran")
	public Long getRealisasiAnggaran() {
		return realisasiAnggaran;
	}

	public void setRealisasiAnggaran(Long realisasiAnggaran) {
		this.realisasiAnggaran = realisasiAnggaran;
	}

	@Column(name = "realisasi_fisik")
	public Integer getRealisasiFisik() {
		return realisasiFisik;
	}

	public void setRealisasiFisik(Integer realisasiFisik) {
		this.realisasiFisik = realisasiFisik;
	}

	@Transient
	public List<Anggaran> getDaftarAnggaran() {
		return daftarAnggaran;
	}

	public void setDaftarAnggaran(List<Anggaran> daftarAnggaran) {
		this.daftarAnggaran = daftarAnggaran;
	}

	@Transient
	public List<Fisik> getDaftarFisik() {
		return daftarFisik;
	}

	public void setDaftarFisik(List<Fisik> daftarFisik) {
		this.daftarFisik = daftarFisik;
	}

	@Transient
	public Long getRencanaAnggaran(Month month) {
		for (Anggaran anggaran : daftarAnggaran) {
			if (month.equals(anggaran.getBulan()))
				return anggaran.getRencana();
		}
		
		return 0L;
	}
	
	@Transient
	public Long getRencanaAnggaranKumulatif(Month month) {
		Long rencanaAnggaranKumulatif = 0L;
		for (Anggaran anggaran : daftarAnggaran) {
			if (month.compareTo(anggaran.getBulan()) < 0 || month.compareTo(anggaran.getBulan()) == 0)
				rencanaAnggaranKumulatif += anggaran.getRencana();
		}
		
		return rencanaAnggaranKumulatif;
	}
	
	@Transient
	public Long getRealisasiAnggaranKumulatif(Month month) {
		Long realisasiAnggaranKumulatif = 0L;
		for (Anggaran anggaran : daftarAnggaran) {
			if (month.compareTo(anggaran.getBulan()) < 0 || month.compareTo(anggaran.getBulan()) == 0)
				realisasiAnggaranKumulatif += anggaran.getRealisasi();
		}
		
		return realisasiAnggaranKumulatif;
	}
	
	@Transient
	public Float getPenyerapan(Month month) {
		if (getRealisasiAnggaranKumulatif(month) <= 0)
			return 0f;
		return ((float)getRealisasiAnggaranKumulatif(month) / (float)getRencanaAnggaranKumulatif(month)) * 100;
	}
	
	@Transient
	public Float getKonsistensi(Month month) {
		if (getPenyerapan(month) <= 0)
			return 0f;
		return getPenyerapan(month) / month.getValue();
	}

	@Override
	public String toString() {
		return "RekapKegiatan [namaUnitKerja=" + namaUnitKerja
				+ ", namaProgram=" + namaProgram + ", namaKegiatan="
				+ namaKegiatan + ", paguAnggaran=" + paguAnggaran
				+ ", realisasiAnggaran=" + realisasiAnggaran
				+ ", realisasiFisik=" + realisasiFisik + "]";
	}
}
