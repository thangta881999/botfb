package com.TP.DTO;

import com.TP.entity.SanPham;

import java.util.List;
import java.util.Set;

public class DanhMucDTO {
	int madanhmuc;
	public int getMadanhmuc() {
		return madanhmuc;
	}
	List<DanhMucDTO> childrentsDanhMuc;
	public List<DanhMucDTO> getChildrentsDanhMuc() {
		return childrentsDanhMuc;
	}
	public void setChildrentsDanhMuc(List<DanhMucDTO> childrentsDanhMuc) {
		this.childrentsDanhMuc = childrentsDanhMuc;
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
	String tendanhmuc;
	Set<SanPham> danhsachsanpham;
	int parent_madanhmuc;
	public int getParent_madanhmuc() {
		return parent_madanhmuc;
	}
	public void setParent_madanhmuc(int parent_madanhmuc) {
		this.parent_madanhmuc = parent_madanhmuc;
	}

}
