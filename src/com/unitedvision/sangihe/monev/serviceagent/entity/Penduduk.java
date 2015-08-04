package com.unitedvision.sangihe.monev.serviceagent.entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.unitedvision.sangihe.monev.util.DateUtil;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Penduduk {

	private long id;
	private String nik;
	private String nama;
	private Date tanggalLahir;
	private Kontak kontak;
	
	public Penduduk() {
		super();
		kontak = new Kontak();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNik() {
		return nik;
	}

	public void setNik(String nik) {
		this.nik = nik;
	}

	public String getNama() {
		return nama;
	}

	public void setNama(String nama) {
		this.nama = nama;
	}

	public Date getTanggalLahir() {
		return tanggalLahir;
	}

	public void setTanggalLahir(Date tanggalLahir) {
		this.tanggalLahir = tanggalLahir;
	}

	public String getTanggalLahirStr() {
		return DateUtil.toStringDate(tanggalLahir, "-");
	}
	
	public void setTanggalLahirStr(String string) {
		setTanggalLahir(DateUtil.getDate(string, "-"));
	}

	
	public Kontak getKontak() {
		return kontak;
	}

	public void setKontak(Kontak kontak) {
		this.kontak = kontak;
	}

	public String getEmail() {
		return kontak.getEmail();
	}

	public void setEmail(String email) {
		kontak.setEmail(email);
	}

	public String getTelepon() {
		return kontak.getTelepon();
	}

	public void setTelepon(String telepon) {
		kontak.setTelepon(telepon);
	}

	public static class Kontak {
		
		private String email;
		private String telepon;
		
		public Kontak() {
			super();
		}

		public String getEmail() {
			return email;
		}

		public void setEmail(String email) {
			this.email = email;
		}

		public String getTelepon() {
			return telepon;
		}

		public void setTelepon(String telepon) {
			this.telepon = telepon;
		}

		@Override
		public int hashCode() {
			final int prime = 31;
			int result = 1;
			result = prime * result + ((email == null) ? 0 : email.hashCode());
			result = prime * result
					+ ((telepon == null) ? 0 : telepon.hashCode());
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
			Kontak other = (Kontak) obj;
			if (email == null) {
				if (other.email != null)
					return false;
			} else if (!email.equals(other.email))
				return false;
			if (telepon == null) {
				if (other.telepon != null)
					return false;
			} else if (!telepon.equals(other.telepon))
				return false;
			return true;
		}
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (int) (id ^ (id >>> 32));
		result = prime * result + ((kontak == null) ? 0 : kontak.hashCode());
		result = prime * result + ((nama == null) ? 0 : nama.hashCode());
		result = prime * result + ((nik == null) ? 0 : nik.hashCode());
		result = prime * result
				+ ((tanggalLahir == null) ? 0 : tanggalLahir.hashCode());
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
		Penduduk other = (Penduduk) obj;
		if (id != other.id)
			return false;
		if (kontak == null) {
			if (other.kontak != null)
				return false;
		} else if (!kontak.equals(other.kontak))
			return false;
		if (nama == null) {
			if (other.nama != null)
				return false;
		} else if (!nama.equals(other.nama))
			return false;
		if (nik == null) {
			if (other.nik != null)
				return false;
		} else if (!nik.equals(other.nik))
			return false;
		if (tanggalLahir == null) {
			if (other.tanggalLahir != null)
				return false;
		} else if (!tanggalLahir.equals(other.tanggalLahir))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Penduduk [id=" + id + ", nik=" + nik + ", nama=" + nama
				+ ", tanggalLahir=" + tanggalLahir + ", kontak=" + kontak + "]";
	}
	
}
