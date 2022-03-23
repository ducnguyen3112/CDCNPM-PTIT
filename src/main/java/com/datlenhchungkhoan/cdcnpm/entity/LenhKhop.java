package com.datlenhchungkhoan.cdcnpm.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "LENHKHOP")
public class LenhKhop {
	@Id
	@Column(name = "IDKHOP")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	@Column(name = "NGAYKHOP")
	private Date ngayKhop;
	@Column(name = "SOLUONGKHOP")
	private int soLuongKhop;
	@Column(name = "GIAKHOP")
	private float giaKhop;
	@ManyToOne(fetch = FetchType.LAZY,cascade = {CascadeType.DETACH,CascadeType.MERGE,CascadeType.PERSIST,CascadeType.REFRESH})
	@JoinColumn(name = "IDLENHDAT")
	private LenhDat lenhDat;
	public LenhKhop() {
		// TODO Auto-generated constructor stub
	}
	public LenhKhop(Date ngayKhop, int soLuongKhop, float giaKhop, LenhDat lenhDat) {
		this.ngayKhop = ngayKhop;
		this.soLuongKhop = soLuongKhop;
		this.giaKhop = giaKhop;
		this.lenhDat = lenhDat;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Date getNgayKhop() {
		return ngayKhop;
	}
	public void setNgayKhop(Date ngayKhop) {
		this.ngayKhop = ngayKhop;
	}
	public int getSoLuongKhop() {
		return soLuongKhop;
	}
	public void setSoLuongKhop(int soLuongKhop) {
		this.soLuongKhop = soLuongKhop;
	}
	public float getGiaKhop() {
		return giaKhop;
	}
	public void setGiaKhop(float giaKhop) {
		this.giaKhop = giaKhop;
	}
	public LenhDat getLenhDat() {
		return lenhDat;
	}
	public void setLenhDat(LenhDat lenhDat) {
		this.lenhDat = lenhDat;
	}
	
}
