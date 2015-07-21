package com.unitedvision.sangihe.monev.entity.rest;

import java.io.Serializable;

import com.unitedvision.sangihe.monev.util.DateUtil;

import java.sql.Date;
import java.util.List;

/**
 * The persistent class for the token database table.
 * 
 * Deddy Christoper Kakunsi
 * 
 * deddy.kakunsi@gmail.com
 */
public class Token implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String token;
	private StatusToken status;
	private Date tanggalBuat;
	private Date tanggalExpire;
	
	private Pegawai pegawai;

	public enum StatusToken {
		AKTIF,
		LOCKED
	}
	
	public Token() {
		super();
	}
	
	public String getToken() {
		return this.token;
	}

	public void setToken(String token) {
		this.token = token;
	}

	public StatusToken getStatus() {
		return this.status;
	}

	public void setStatus(StatusToken status) {
		this.status = status;
	}

	public Date getTanggalBuat() {
		return this.tanggalBuat;
	}

	public void setTanggalBuat(Date tanggalBuat) {
		this.tanggalBuat = tanggalBuat;
	}
	
	public String getTanggalStr() {
		return DateUtil.toFormattedStringDate(tanggalBuat, "/");
	}
	
	public void setTanggalStr(String tanggalStr) { }


	public Date getTanggalExpire() {
		return this.tanggalExpire;
	}

	public void setTanggalExpire(Date tanggalExpire) {
		this.tanggalExpire = tanggalExpire;
	}
	
	public String getExpireStr() {
		return DateUtil.toFormattedStringDate(tanggalExpire, "/");
	}

	public void setExpireDate(Date expire) { }


	public Pegawai getpegawai() {
		return this.pegawai;
	}

	public void setPegawai(Pegawai pegawai) {
		this.pegawai = pegawai;
	}

	public List<Operator> getDaftarOperator() {
		return pegawai.getDaftarOperator();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((pegawai == null) ? 0 : pegawai.hashCode());
		result = prime * result
				+ ((tanggalBuat == null) ? 0 : tanggalBuat.hashCode());
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
		Token other = (Token) obj;
		if (pegawai == null) {
			if (other.pegawai != null)
				return false;
		} else if (!pegawai.equals(other.pegawai))
			return false;
		if (status != other.status)
			return false;
		if (tanggalBuat == null) {
			if (other.tanggalBuat != null)
				return false;
		} else if (!tanggalBuat.equals(other.tanggalBuat))
			return false;
		if (tanggalExpire == null) {
			if (other.tanggalExpire != null)
				return false;
		} else if (!tanggalExpire.equals(other.tanggalExpire))
			return false;
		if (token == null) {
			if (other.token != null)
				return false;
		} else if (!token.equals(other.token))
			return false;
		return true;
	}
	
}