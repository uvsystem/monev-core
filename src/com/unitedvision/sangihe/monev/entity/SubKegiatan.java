package com.unitedvision.sangihe.monev.entity;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "kegiatan")
@DiscriminatorValue("SUB_KEGIATAN")
public class SubKegiatan extends Kegiatan {

	private Kegiatan kegiatan;
	
	public SubKegiatan() {
		super();
	}

	@JsonBackReference("sub_kegiatan")
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "parent", nullable = false)
	public Kegiatan getKegiatan() {
		return kegiatan;
	}

	public void setKegiatan(Kegiatan kegiatan) {
		this.kegiatan = kegiatan;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((kegiatan == null) ? 0 : kegiatan.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		SubKegiatan other = (SubKegiatan) obj;
		if (kegiatan == null) {
			if (other.kegiatan != null)
				return false;
		} else if (!kegiatan.equals(other.kegiatan))
			return false;
		return true;
	}
}
