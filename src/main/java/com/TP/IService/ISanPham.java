package com.TP.IService;

import com.TP.DTO.SanPhamDTO;
import com.TP.entity.SanPham;

import java.util.List;

public interface ISanPham {
	void indexBooks() throws Exception;
	List<SanPhamDTO> findAll(int offset,int limit);
	SanPham LayDanhSachChiTietSanPhamTheoMa(int masanpham);
	List<SanPhamDTO> getProductRecommend(Integer[] ids);
	List<SanPhamDTO> LayDanhSachSanPhamTheoMaDanhMuc(int madanhmuc,int offset, int limit);
	boolean XoaSanPhamTheoMaSanPham(int masp);
	boolean themSanPham(SanPham sanPham);
	int save2(SanPham sanPham);
	boolean capnhatSanPham(SanPham sanPham);
	List<SanPhamDTO> search(String keyword,int offset, int limit);
	List<SanPhamDTO> findByCategoryAndOrder(int madanhmuc,String orderby,String order,int offset,int limit);
	List<SanPhamDTO> sortAll(String orderby,String order,int offset,int limit);
}
