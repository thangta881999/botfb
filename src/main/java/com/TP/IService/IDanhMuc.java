package com.TP.IService;

import com.TP.DTO.DanhMucDTO;
import com.TP.entity.DanhMucSanPham;

import java.util.List;

public interface IDanhMuc {
	List<DanhMucSanPham> findAll(int offset, int limit);
	boolean deleteChildrent(int madm);
	boolean deleteAll(int madm);
	boolean save(DanhMucSanPham danhmuc);
	DanhMucDTO findById(int madm);
	public List<DanhMucSanPham> findByParent();
	public List<DanhMucSanPham> findByChildren(int parent_madanhmuc);
	public List<DanhMucDTO>CreateMenu();
}
