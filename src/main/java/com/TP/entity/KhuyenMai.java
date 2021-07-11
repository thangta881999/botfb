package com.TP.entity;

import javax.persistence.*;
import java.util.Set;

@Entity(name = "KHUYENMAI")
public class KhuyenMai {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int makhuyenmai;

	String tenkhuyenmai;
	String thoigianbatdau;
	String thoigianketthuc;
	String mota;
	String hinhkhuyenmai;
	int giamgia;

	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	
	 
	  @JoinTable(name = "chitietkhuyenmai", joinColumns = @JoinColumn(name = "makhuyenmai"),
	  inverseJoinColumns = @JoinColumn(name = "masanpham"))
	Set<SanPham> danhSachSanPham;
	
	public Set<SanPham> getDanhSachSanPham() {
		return danhSachSanPham;
	}

	public void setDanhSachSanPham(Set<SanPham> danhSachSanPham) {
		this.danhSachSanPham = danhSachSanPham;
	}

	public int getGiamgia() {
		return giamgia;
	}

	public void setGiamgia(int giamgia) {
		this.giamgia = giamgia;
	}

	public int getMakhuyenmai() {
		return makhuyenmai;
	}

	public void setMakhuyenmai(int makhuyenmai) {
		this.makhuyenmai = makhuyenmai;
	}

	public String getTenkhuyenmai() {
		return tenkhuyenmai;
	}

	public void setTenkhuyenmai(String tenkhuyenmai) {
		this.tenkhuyenmai = tenkhuyenmai;
	}

	public String getThoigianbatdau() {
		return thoigianbatdau;
	}

	public void setThoigianbatdau(String thoigianbatdau) {
		this.thoigianbatdau = thoigianbatdau;
	}

	public String getThoigianketthuc() {
		return thoigianketthuc;
	}

	public void setThoigianketthuc(String thoigianketthuc) {
		this.thoigianketthuc = thoigianketthuc;
	}

	public String getMota() {
		return mota;
	}

	public void setMota(String mota) {
		this.mota = mota;
	}

	public String getHinhkhuyenmai() {
		return hinhkhuyenmai;
	}

	public void setHinhkhuyenmai(String hinhkhuyenmai) {
		this.hinhkhuyenmai = hinhkhuyenmai;
	}
}
