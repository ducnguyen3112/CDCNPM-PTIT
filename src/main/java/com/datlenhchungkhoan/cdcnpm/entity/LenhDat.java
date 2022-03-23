package com.datlenhchungkhoan.cdcnpm.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

import com.datlenhchungkhoan.cdcnpm.validation.Divisible;

@Entity
@Table(name = "LENHDAT")
public class LenhDat {
	@Id
	@Column(name = "ID")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Pattern(regexp = "^[a-zA-Z]+$",message = "Giá trị không chứa số, khoảng trắng hay kí tự đặc biệt!")
	@Column(name = "MACP")
	private String maCoPhieu;
	@Column(name = "NGAYDAT")
	private Date ngayDat;
	@Column(name = "LOAIGD")
	private char loaiGD;
	@Column(name = "LOAILENH")
	private String loaiLenh;
	@Pattern(regexp = "^[0-9]+$",message = "Giá trị phải là số nguyên dương!")
	@Divisible
	@Column(name = "SOLUONG")
	private String soLuong;
	@Pattern(regexp = "^[0-9]+$",message = "Giá trị phải là số nguyên dương!")
	@Column(name = "GIADAT")
	private String giaDat;
	@Column(name = "TRANGTHAILENH")
	private String trangThaiLenh;
	@OneToMany(mappedBy = "lenhDat", cascade = CascadeType.ALL)
	private List<LenhKhop> dsLenhKhop;
	public LenhDat() {
		// TODO Auto-generated constructor stub
	}
	public LenhDat(
			@Pattern(regexp = "^[a-zA-Z]+$", message = "Giá trị không chứa số hay kí tự đặc biệt!") String maCoPhieu,
			Date ngayDat, char loaiGD, String loaiLenh,
			@Pattern(regexp = "^[0-9]+$", message = "Giá trị phải là số nguyên dương!") String soLuong,
			@Pattern(regexp = "^[0-9]+$", message = "Giá trị phải là số nguyên dương!") String giaDat,
			String trangThaiLenh, List<LenhKhop> dsLenhKhop) {
		this.maCoPhieu = maCoPhieu;
		this.ngayDat = ngayDat;
		this.loaiGD = loaiGD;
		this.loaiLenh = loaiLenh;
		this.soLuong = soLuong;
		this.giaDat = giaDat;
		this.trangThaiLenh = trangThaiLenh;
		this.dsLenhKhop = dsLenhKhop;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getMaCoPhieu() {
		return maCoPhieu;
	}
	public void setMaCoPhieu(String maCoPhieu) {
		this.maCoPhieu = maCoPhieu;
	}
	public Date getNgayDat() {
		return ngayDat;
	}
	public void setNgayDat(Date ngayDat) {
		this.ngayDat = ngayDat;
	}
	public char getLoaiGD() {
		return loaiGD;
	}
	public void setLoaiGD(char loaiGD) {
		this.loaiGD = loaiGD;
	}
	public String getLoaiLenh() {
		return loaiLenh;
	}
	public void setLoaiLenh(String loaiLenh) {
		this.loaiLenh = loaiLenh;
	}
	public String getSoLuong() {
		return soLuong;
	}
	public void setSoLuong(String soLuong) {
		this.soLuong = soLuong;
	}
	public String getGiaDat() {
		return giaDat;
	}
	public void setGiaDat(String giaDat) {
		this.giaDat = giaDat;
	}
	public String getTrangThaiLenh() {
		return trangThaiLenh;
	}
	public void setTrangThaiLenh(String trangThaiLenh) {
		this.trangThaiLenh = trangThaiLenh;
	}
	public List<LenhKhop> getDsLenhKhop() {
		return dsLenhKhop;
	}
	public void setDsLenhKhop(List<LenhKhop> dsLenhKhop) {
		this.dsLenhKhop = dsLenhKhop;
	}
	
}
