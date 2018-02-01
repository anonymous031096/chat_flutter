package org.duc.qlkhoahoc.entity;

import static javax.persistence.GenerationType.IDENTITY;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "khoa_hoc", catalog = "qlkhoahoc")
public class KhoaHoc implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	private Integer idkhoaHoc;
	private String tenKhoaHoc;
	private Set<SinhVien> sinhViens = new HashSet<SinhVien>(0);

	public KhoaHoc() {
	}

	public KhoaHoc(String tenKhoaHoc) {
		this.tenKhoaHoc = tenKhoaHoc;
	}

	public KhoaHoc(String tenKhoaHoc, Set<SinhVien> sinhViens) {
		this.tenKhoaHoc = tenKhoaHoc;
		this.sinhViens = sinhViens;
	}

	@Id
	@GeneratedValue(strategy = IDENTITY)

	@Column(name = "idkhoa_hoc", unique = true, nullable = false)
	public Integer getIdkhoaHoc() {
		return this.idkhoaHoc;
	}

	public void setIdkhoaHoc(Integer idkhoaHoc) {
		this.idkhoaHoc = idkhoaHoc;
	}

	@Column(name = "ten_khoa_hoc", nullable = false, length = 50)
	public String getTenKhoaHoc() {
		return this.tenKhoaHoc;
	}

	public void setTenKhoaHoc(String tenKhoaHoc) {
		this.tenKhoaHoc = tenKhoaHoc;
	}

	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "lop", catalog = "qlkhoahoc", joinColumns = {
			@JoinColumn(name = "idkhoa_hoc", nullable = false, updatable = false) }, inverseJoinColumns = {
					@JoinColumn(name = "idsinh_vien", nullable = false, updatable = false) })
	public Set<SinhVien> getSinhViens() {
		return this.sinhViens;
	}

	public void setSinhViens(Set<SinhVien> sinhViens) {
		this.sinhViens = sinhViens;
	}

}
