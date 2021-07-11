package com.TP.entity;

import org.hibernate.search.annotations.Index;
import org.hibernate.search.annotations.*;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Set;

@Entity(name = "SANPHAM")
@Indexed
public class SanPham {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int masanpham;
	@OneToMany(mappedBy = "sanPham", fetch = FetchType.LAZY)
	private List<Review> reviews;
	@OneToOne()
	@JoinColumn(name = "madanhmuc")
	DanhMucSanPham danhMucSanPham;
	
	@NotNull(message = "Không được bỏ trống") 
	@Size(min=10, max=255, message="Nhập tên sản phẩm từ 10-255 ký tự")
	 @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO) 
	String tensanpham;
	
	@NotEmpty(message = "Không được bỏ trống") 
	@Size(max=14 ,message 
		      = "Số tiền dưới 100 tỷ")
	String giatien;
	 @Field(index= Index.YES, analyze= Analyze.YES, store= Store.NO) 
	String mota;
	@NotEmpty(message = "Không được bỏ trống") 
	String hinhsanpham;
//	@NotEmpty(message = "Không được bỏ trống")
	String danhcho;

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "masanpham")
	Set<ChiTietSanPham> chiTietSanPham;
	
	
	 
	@ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	  
	  @JoinTable(name = "chitietkhuyenmai", joinColumns = @JoinColumn(name = "masanpham"),
	  inverseJoinColumns = @JoinColumn(name = "makhuyenmai"))
	Set<KhuyenMai> danhSachKhuyenMai;
	
	public Set<KhuyenMai> getDanhSachKhuyenMai() {
		return danhSachKhuyenMai;
	}

	public void setDanhSachKhuyenMai(Set<KhuyenMai> danhSachKhuyenMai) {
		this.danhSachKhuyenMai = danhSachKhuyenMai;
	}

	public Set<ChiTietSanPham> getChiTietSanPham() {
		return chiTietSanPham;
	}

	public void setChiTietSanPham(Set<ChiTietSanPham> chiTietSanPham) {
		this.chiTietSanPham = chiTietSanPham;
	}

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
}
