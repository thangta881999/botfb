package com.TP.entity;

import javax.persistence.*;

@Entity(name = "CHITIETSANPHAM")
public class ChiTietSanPham {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int machitietsanpham;

	@OneToOne()
	@JoinColumn(name = "masanpham")
	SanPham sanPham;

	@OneToOne()
	@JoinColumn(name = "masize")
	SizeSanPham sizeSanPham;

	@OneToOne()
	@JoinColumn(name = "mamau")
	MauSanPham mauSanPham;

	int soluong;
	String ngaynhap;

	public int getMachitietsanpham() {
		return machitietsanpham;
	}

	public void setMachitietsanpham(int machitietsanpham) {
		this.machitietsanpham = machitietsanpham;
	}

	public SanPham getSanPham() {
		return sanPham;
	}

	public void setSanPham(SanPham sanPham) {
		this.sanPham = sanPham;
	}

	public SizeSanPham getSizeSanPham() {
		return sizeSanPham;
	}

	public void setSizeSanPham(SizeSanPham sizeSanPham) {
		this.sizeSanPham = sizeSanPham;
	}

	public MauSanPham getMauSanPham() {
		return mauSanPham;
	}

	public void setMauSanPham(MauSanPham mauSanPham) {
		this.mauSanPham = mauSanPham;
	}

	public int getSoluong() {
		return soluong;
	}

	public void setSoluong(int soluong) {
		this.soluong = soluong;
	}

	public String getNgaynhap() {
		return ngaynhap;
	}

	public void setNgaynhap(String ngaynhap) {
		this.ngaynhap = ngaynhap;
	}
}
