package com.TP.DTO;

import com.TP.entity.ChiTietHoaDon;

import java.util.Set;

public class HoaDonDTO extends BaseDTO {
	int mahoadon;
	String tenkhachhang;
	String sodt;
	String diachigiaohang;
	TinhTrangEnum tinhtrang;
	Boolean thanhtoan;
	public Boolean getThanhtoan() {
		return thanhtoan;
	}

	public void setThanhtoan(Boolean thanhtoan) {
		this.thanhtoan = thanhtoan;
	}

	String hinhthucgiaohang;
	String ghichu;

	String tongtien;

	public String getTongtien() {
		return tongtien;
	}

	public void setTongtien(String tongtien) {
		this.tongtien = tongtien;
	}

	Set<ChiTietHoaDon> danhsachChiTietHoaDon;

	public int getMahoadon() {
		return mahoadon;
	}

	public void setMahoadon(int mahoadon) {
		this.mahoadon = mahoadon;
	}

	public String getTenkhachhang() {
		return tenkhachhang;
	}

	public void setTenkhachhang(String tenkhachhang) {
		this.tenkhachhang = tenkhachhang;
	}

	public String getSodt() {
		return sodt;
	}

	public void setSodt(String sodt) {
		this.sodt = sodt;
	}

	public String getDiachigiaohang() {
		return diachigiaohang;
	}

	public void setDiachigiaohang(String diachigiaohang) {
		this.diachigiaohang = diachigiaohang;
	}

	public TinhTrangEnum getTinhtrang() {
		return tinhtrang;
	}

	public void setTinhtrang(TinhTrangEnum tinhtrang) {
		this.tinhtrang = tinhtrang;
	}

	public String getHinhthucgiaohang() {
		return hinhthucgiaohang;
	}

	public void setHinhthucgiaohang(String hinhthucgiaohang) {
		this.hinhthucgiaohang = hinhthucgiaohang;
	}

	public String getGhichu() {
		return ghichu;
	}

	public void setGhichu(String ghichu) {
		this.ghichu = ghichu;
	}

	public Set<ChiTietHoaDon> getDanhsachChiTietHoaDon() {
		return danhsachChiTietHoaDon;
	}

	public void setDanhsachChiTietHoaDon(Set<ChiTietHoaDon> danhsachChiTietHoaDon) {
		this.danhsachChiTietHoaDon = danhsachChiTietHoaDon;
	}

}
