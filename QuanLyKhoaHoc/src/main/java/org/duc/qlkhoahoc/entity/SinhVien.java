package org.duc.qlkhoahoc.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "sinh_vien", catalog = "qlkhoahoc")
public class SinhVien implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idsinhVien;
	private String name;
	private String phone;
	private String passport;
	private Set<KhoaHoc> khoaHocs = new HashSet<KhoaHoc>(0);

	public SinhVien() {
	}

	public SinhVien(String name, String phone, String passport) {
		this.name = name;
		this.phone = phone;
		this.passport = passport;
	}

	public SinhVien(String name, String phone, String passport, Set<KhoaHoc> khoaHocs) {
		this.name = name;
		this.phone = phone;
		this.passport = passport;
		this.khoaHocs = khoaHocs;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idsinh_vien", unique = true, nullable = false)
	public Integer getIdsinhVien() {
		return this.idsinhVien;
	}

	public void setIdsinhVien(Integer idsinhVien) {
		this.idsinhVien = idsinhVien;
	}

	@Column(name = "name", nullable = false, length = 45)
	public String getName() {
		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Column(name = "phone", nullable = false, length = 45)
	public String getPhone() {
		return this.phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Column(name = "passport", nullable = false, length = 45)
	public String getPassport() {
		return this.passport;
	}

	public void setPassport(String passport) {
		this.passport = passport;
	}

	@ManyToMany(fetch = FetchType.LAZY, mappedBy = "sinhViens")
	public Set<KhoaHoc> getKhoaHocs() {
		return this.khoaHocs;
	}

	public void setKhoaHocs(Set<KhoaHoc> khoaHocs) {
		this.khoaHocs = khoaHocs;
	}

}
