package com.unitedvision.sangihe.monev.entity;

import java.time.Month;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table(name = "fisik")
public class Fisik {

	private Long id;
	private Integer tahun;
	private Month bulan;
	private Integer realisasi;
	
	private Kegiatan kegiatan;
	
	private Set<Foto> daftarFoto;
	
	public Fisik() {
		super();
		daftarFoto = new HashSet<>();
	}

	public Fisik(Kegiatan kegiatan) {
		this();
		setKegiatan(kegiatan);
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "tahun", nullable = false)
	public Integer getTahun() {
		return tahun;
	}

	public void setTahun(Integer tahun) {
		this.tahun = tahun;
	}

	@Column(name = "bulan", nullable = false)
	public Month getBulan() {
		return bulan;
	}

	public void setBulan(Month bulan) {
		this.bulan = bulan;
	}

	@Column(name = "realisasi", nullable = false)
	public Integer getRealisasi() {
		return realisasi;
	}

	public void setRealisasi(Integer realisasi) {
		this.realisasi = realisasi;
	}

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "kegiatan", nullable = false)
	public Kegiatan getKegiatan() {
		return kegiatan;
	}

	public void setKegiatan(Kegiatan kegiatan) {
		this.kegiatan = kegiatan;
	}

	//@CollectionOfElements
	//@JoinTable(name = "foto", joinColumns = @JoinColumn(name = "fisik"))
	@Transient
	public Set<Foto> getDaftarFoto() {
		return daftarFoto;
	}

	public void setDaftarFoto(Set<Foto> daftarFoto) {
		this.daftarFoto = daftarFoto;
	}
	
	public void addFoto(Foto foto) {
		foto.setFisik(this);
		daftarFoto.add(foto);
	}
	
	public void removeFoto(Foto foto) {
		foto.setFisik(null);
		daftarFoto.remove(foto);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bulan == null) ? 0 : bulan.hashCode());
		result = prime * result
				+ ((daftarFoto == null) ? 0 : daftarFoto.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result
				+ ((kegiatan == null) ? 0 : kegiatan.hashCode());
		result = prime * result
				+ ((realisasi == null) ? 0 : realisasi.hashCode());
		result = prime * result + ((tahun == null) ? 0 : tahun.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Fisik other = (Fisik) obj;
		if (bulan != other.bulan)
			return false;
		if (daftarFoto == null) {
			if (other.daftarFoto != null)
				return false;
		} else if (!daftarFoto.equals(other.daftarFoto))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (kegiatan == null) {
			if (other.kegiatan != null)
				return false;
		} else if (!kegiatan.equals(other.kegiatan))
			return false;
		if (realisasi == null) {
			if (other.realisasi != null)
				return false;
		} else if (!realisasi.equals(other.realisasi))
			return false;
		if (tahun == null) {
			if (other.tahun != null)
				return false;
		} else if (!tahun.equals(other.tahun))
			return false;
		return true;
	}
}
