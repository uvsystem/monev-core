package com.unitedvision.sangihe.monev.entity.rest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import com.unitedvision.sangihe.monev.entity.UnitKerja;

public class Pegawai {

	private long id;
	private String nip;
	private Penduduk penduduk;
	private UnitKerja unitKerja;
	
	private List<Operator> daftarOperator;
	
	public Pegawai() {
		super();
		penduduk = new Penduduk();
		daftarOperator = new ArrayList<>();
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

	public List<Operator> getDaftarOperator() {
		return daftarOperator;
	}
}
