package com.unitedvision.sangihe.monev.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.unitedvision.sangihe.monev.exception.CredentialException;

/**
 * Mapped tabel operator.
 * 
 * @author Deddy Christoper Kakunsi
 *
 */
@Entity
@Table(name = "operator")
public class Operator {

	/**
	 * Hak akses operator.
	 * 
	 */
	public enum Role {
		ADMIN,
		OPERATOR
	}

	private int id;
	private String username;
	private String password;
	private Role role;
	
	private Skpd skpd;

	
	/**
	 * Return id operator.
	 * @return id operator.
	 */
	@Id
	@GeneratedValue
	public int getId() {
		return id;
	}

	/**
	 * Atur id operator.
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Return username operator.
	 * @return username
	 */
	@Column(name = "username")
	public String getUsername() {
		return username;
	}

	/**
	 * Atur username operator.
	 * @param username
	 * @throws CredentialException username null atau kosong 
	 */
	public void setUsername(String username) throws CredentialException {
		if (username == null || username.equals(""))
			throw new CredentialException("Username tidak boleh kosong");

		this.username = username;
	}

	/**
	 * Return password operator.
	 * @return password operator.
	 */
	@Column(name = "password")
	public String getPassword() {
		return password;
	}

	/**
	 * Atur password operator.
	 * @param password
	 * @throws CredentialException password null atau kosong.
	 */
	public void setPassword(String password) throws CredentialException {
		if (password == null || password.equals(""))
			throw new CredentialException("Password tidak boleh kosong");
			
		this.password = password;
	}

	/**
	 * Return role operator.
	 * @return role operator.
	 */
	public Role getRole() {
		return role;
	}

	/**
	 * Atur role operator.
	 * @param role
	 */
	public void setRole(Role role) {
		this.role = role;
	}

	/**
	 * Return SKPD operator.
	 * @return SKPD operator.
	 */
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "skpd")
	public Skpd getSkpd() {
		return skpd;
	}

	/**
	 * Atur SKPD operator.
	 * @param skpd
	 */
	public void setSkpd(Skpd skpd) {
		this.skpd = skpd;
	}
}
