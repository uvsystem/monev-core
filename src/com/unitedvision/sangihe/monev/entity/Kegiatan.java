package com.unitedvision.sangihe.monev.entity;

import java.util.ArrayList;
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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

/**
 * Mapping tabel Kegiatan.
 * 
 * @author Deddy Christoper Kakunsi
 *
 */
@Entity
@Table(name = "kegiatan")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(
	name = "discriminator",
	discriminatorType = DiscriminatorType.STRING
)
@DiscriminatorValue("KEGIATAN")
public class Kegiatan {

	private Long id;
	private String nama;
	private Long paguAnggaran;
	
	private Program program;
	
	private List<SubKegiatan> daftarSubKegiatan;
	private List<Anggaran> daftarAnggaran;
	private List<Fisik> daftarFisik;
	
	public Kegiatan() {
		super();
		daftarSubKegiatan = new ArrayList<>();
		daftarAnggaran = new ArrayList<>();
		daftarFisik = new ArrayList<>();
	}

	public Kegiatan(Program program) {
		this();
		setProgram(program);
	}

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@Column(name = "nama", nullable = false)
	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	@Column(name = "pagu_anggaran", nullable = false)
	public Long getPaguAnggaran() {
		return paguAnggaran;
	}

	public void setPaguAnggaran(Long paguAnggaran) {
		this.paguAnggaran = paguAnggaran;
	}

	@JsonBackReference
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "program", nullable = false)
	public Program getProgram() {
		return program;
	}

	public void setProgram(Program program) {
		this.program = program;
	}

	@OneToMany(mappedBy = "kegiatan", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	public List<SubKegiatan> getDaftarSubKegiatan() {
		return daftarSubKegiatan;
	}

	public void setDaftarSubKegiatan(List<SubKegiatan> daftarSubKegiatan) {
		this.daftarSubKegiatan = daftarSubKegiatan;
	}

	@OneToMany(mappedBy = "kegiatan", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	public List<Anggaran> getDaftarAnggaran() {
		return daftarAnggaran;
	}

	public void setDaftarAnggaran(List<Anggaran> daftarAnggaran) {
		this.daftarAnggaran = daftarAnggaran;
	}

	@OneToMany(mappedBy = "kegiatan", fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
	public List<Fisik> getDaftarFisik() {
		return daftarFisik;
	}

	public void setDaftarFisik(List<Fisik> daftarFisik) {
		this.daftarFisik = daftarFisik;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((nama == null) ? 0 : nama.hashCode());
		result = prime * result
				+ ((paguAnggaran == null) ? 0 : paguAnggaran.hashCode());
		result = prime * result + ((program == null) ? 0 : program.hashCode());
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
		Kegiatan other = (Kegiatan) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (nama == null) {
			if (other.nama != null)
				return false;
		} else if (!nama.equals(other.nama))
			return false;
		if (paguAnggaran == null) {
			if (other.paguAnggaran != null)
				return false;
		} else if (!paguAnggaran.equals(other.paguAnggaran))
			return false;
		if (program == null) {
			if (other.program != null)
				return false;
		} else if (!program.equals(other.program))
			return false;
		return true;
	}
}
