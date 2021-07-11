package com.TP.DTO;

import com.TP.entity.ChiTietSanPham;
import com.TP.entity.DanhMucSanPham;

import java.util.Set;

public class SanPhamDTO {
	int masanpham;

	DanhMucSanPham danhMucSanPham;

	String tensanpham;
	String giatien;
	String mota;
	String hinhsanpham;
	String danhcho;

	Set<ChiTietSanPham> chiTietSanPham;

	public int getMasanpham() {
		return masanpham;
	}

	public void setMasanpham(int masanpham) {
		this.masanpham = masanpham;
	}

	public DanhMucSanPham getDanhMucSanPham() {
		return danhMucSanPham;
	}

	public void setDanhMucSanPham(DanhMucSanPham danhMucSanPham) {
		this.danhMucSanPham = danhMucSanPham;
	}

	public String getTensanpham() {
		return tensanpham;
	}

	public void setTensanpham(String tensanpham) {
		this.tensanpham = tensanpham;
	}

	public String getGiatien() {
		return giatien;
	}

	public void setGiatien(String giatien) {
		this.giatien = giatien;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public String getHinhsanpham() {
		return hinhsanpham;
	}

	public void setHinhsanpham(String hinhsanpham) {
		this.hinhsanpham = hinhsanpham;
	}

	public String getDanhcho() {
		return danhcho;
	}

	public void setDanhcho(String danhcho) {
		this.danhcho = danhcho;
	}

	public Set<ChiTietSanPham> getChiTietSanPham() {
		return chiTietSanPham;
	}

	public void setChiTietSanPham(Set<ChiTietSanPham> chiTietSanPham) {
		this.chiTietSanPham = chiTietSanPham;
	}
	
}
