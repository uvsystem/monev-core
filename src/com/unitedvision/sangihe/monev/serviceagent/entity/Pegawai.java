package com.unitedvision.sangihe.monev.serviceagent.entity;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.unitedvision.sangihe.monev.entity.UnitKerja;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pegawai {

	private long id;
	private String nip;
	private Penduduk penduduk;
	private UnitKerja unitKerja;
	private String password;

	private List<Operator> listOperator = new ArrayList<>();
	
	public Pegawai() {
		super();
		penduduk = new Penduduk();
	}

	public Pegawai(UnitKerja unitKerja) {
		this();
		setUnitKerja(unitKerja);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNip() {
		return nip;
	}

	public void setNip(String nip) {
		this.nip = nip;
	}

	public Penduduk getPenduduk() {
		return penduduk;
	}

	public void setPenduduk(Penduduk penduduk) {
		this.penduduk = penduduk;
	}

	public UnitKerja getUnitKerja() {
		return unitKerja;
	}

	public void setUnitKerja(UnitKerja unitKerja) {
		this.unitKerja = unitKerja;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPasswordStr() {
		return password;
	}

	public void setPasswordStr(String password) {
		this.password = password;
	}
	
	public Long getIdPenduduk() {
		return penduduk.getId();
	}
	
	public void setIdPenduduk(long id) {
		penduduk.setId(id);
	}

	public String getNik() {
		return penduduk.getNik();
	}

	public void setNik(String nik) {
		penduduk.setNik(nik);
	}

	public String getNama() {
		return penduduk.getNama();
	}

	public void setNama(String nama) {
		penduduk.setNama(nama);
	}

	public Date getTanggalLahir() {
		return penduduk.getTanggalLahir();
	}

	public void setTanggalLahir(Date tanggalLahir) {
		penduduk.setTanggalLahir(tanggalLahir);
	}

	public String getTanggalLahirStr() {
		return penduduk.getTanggalLahirStr();
	}

	public void setTanggalLahirStr(String tanggalLahirStr) {
		penduduk.setTanggalLahirStr(tanggalLahirStr);
	}
	
	public String getEmail() {
		return penduduk.getEmail();
	}

	public void setEmail(String email) {
		penduduk.setEmail(email);
	}

	public String getTelepon() {
		return penduduk.getTelepon();
	}

	public void setTelepon(String telepon) {
		penduduk.setTelepon(telepon);
	}

	public List<Operator> getListOperator() {
		return listOperator;
	}

	public void setListOperator(List<Operator> operator) {
		this.listOperator = operator;
	}
	
	@Override
	public String toString() {
		return "Pegawai [id=" + id + ", nip=" + nip + ", penduduk=" + penduduk
				+ ", unitKerja=" + unitKerja + "]";
	}
}
