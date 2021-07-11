package com.TP.entity;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity(name = "DANHMUCSANPHAM")
public class DanhMucSanPham {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	int madanhmuc;
	@NotNull
	@Size(min = 2, max = 50,message = "Tên danh mục từ 2-50 ký tự")
	String tendanhmuc;
	
	int parent_madanhmuc;

	public int getParent_madanhmuc() {
		return parent_madanhmuc;
	}

	public void setParent_madanhmuc(int parent_madanhmuc) {
		this.parent_madanhmuc = parent_madanhmuc;
	}

	@OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name="madanhmuc")
	Set<SanPham> danhsachsanpham;
	public int getMadanhmuc() {
		return madanhmuc;
	}

	public void setMadanhmuc(int madanhmuc) {
		this.madanhmuc = madanhmuc;
	}

	public String getTendanhmuc() {
		return tendanhmuc;
	}

	public void setTendanhmuc(String tendanhmuc) {
		this.tendanhmuc = tendanhmuc;
	}



	public Set<SanPham> getDanhsachsanpham() {
		return danhsachsanpham;
	}

	public void setDanhsachsanpham(Set<SanPham> danhsachsanpham) {
		this.danhsachsanpham = danhsachsanpham;
	}
}
