package com.unitedvision.sangihe.monev.serviceagent.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Operator {

	public enum Role {
		ADMIN,
		OPERATOR
	}
	
	private long id;
	private Role role;
	private String kodeAplikasi;
	private String username;

	public Operator() {
		super();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getKodeAplikasi() {
		return kodeAplikasi;
	}

	public void setKodeAplikasi(String kodeAplikasi) {
		this.kodeAplikasi = kodeAplikasi;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result
				+ ((kodeAplikasi == null) ? 0 : kodeAplikasi.hashCode());
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + ((role == null) ? 0 : role.hashCode());
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
		Operator other = (Operator) obj;
		if (kodeAplikasi == null) {
			if (other.kodeAplikasi != null)
				return false;
		} else if (!kodeAplikasi.equals(other.kodeAplikasi))
			return false;
		if (id != other.id)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (role != other.role)
			return false;
		return true;
	}
}
