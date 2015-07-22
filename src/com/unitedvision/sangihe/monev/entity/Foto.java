package com.unitedvision.sangihe.monev.entity;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import org.hibernate.annotations.Parent;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Embeddable
public class Foto {

	private Fisik fisik;
	private String location;
	
	public Foto() {
		super();
	}

	public Foto(String location) {
		super();
		setLocation(location);
	}

	@JsonIgnore
	@Parent
	public Fisik getFisik() {
		return fisik;
	}

	public void setFisik(Fisik fisik) {
		this.fisik = fisik;
	}

	@Column(name = "location", nullable = false)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((fisik == null) ? 0 : fisik.hashCode());
		result = prime * result
				+ ((location == null) ? 0 : location.hashCode());
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
		Foto other = (Foto) obj;
		if (fisik == null) {
			if (other.fisik != null)
				return false;
		} else if (!fisik.equals(other.fisik))
			return false;
		if (location == null) {
			if (other.location != null)
				return false;
		} else if (!location.equals(other.location))
			return false;
		return true;
	}
}
