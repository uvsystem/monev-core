package com.unitedvision.sangihe.monev.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
@Table(name = "unit_kerja")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
	name = "discriminator",
	discriminatorType = DiscriminatorType.STRING
)
@DiscriminatorValue("SKPD")
public class UnitKerja {

	public enum TipeUnitKerja {
		SEKRETARIAT,
		DINAS,
		BADAN,
		UPT,
		BIDANG,
		BAGIAN,
		SEKSI
	}
	
	private long id;
	private String nama;
	private TipeUnitKerja tipe;
	private String singkatan;
	
	private List<SubUnitKerja> daftarSubUnit;
	private List<Program> daftarProgram;
	
	public UnitKerja() {
		super();
	}
	
	@Id
	@GeneratedValue
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	@Column(name = "nama", nullable = false)
	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	@Column(name = "tipe", nullable = false)
	public TipeUnitKerja getTipe() {
		return tipe;
	}

	public void setTipe(TipeUnitKerja tipe) {
		this.tipe = tipe;
	}

	@Column(name = "singkatan")
	public String getSingkatan() {
		return singkatan;
	}

	public void setSingkatan(String singkatan) {
		this.singkatan = singkatan;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "unitKerja", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	public List<SubUnitKerja> getDaftarSubUnit() {
		return daftarSubUnit;
	}

	public void setDaftarSubUnit(List<SubUnitKerja> daftarSubUnit) {
		this.daftarSubUnit = daftarSubUnit;
	}

	@JsonIgnore
	@OneToMany(mappedBy = "unitKerja", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	public List<Program> getDaftarProgram() {
		return daftarProgram;
	}

	public void setDaftarProgram(List<Program> daftarProgram) {
		this.daftarProgram = daftarProgram;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((nama == null) ? 0 : nama.hashCode());
		result = prime * result
				+ ((singkatan == null) ? 0 : singkatan.hashCode());
		result = prime * result + ((tipe == null) ? 0 : tipe.hashCode());
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
		UnitKerja other = (UnitKerja) obj;
		if (id != other.id)
			return false;
		if (nama == null) {
			if (other.nama != null)
				return false;
		} else if (!nama.equals(other.nama))
			return false;
		if (singkatan == null) {
			if (other.singkatan != null)
				return false;
		} else if (!singkatan.equals(other.singkatan))
			return false;
		if (tipe != other.tipe)
			return false;
		return true;
	}

}
