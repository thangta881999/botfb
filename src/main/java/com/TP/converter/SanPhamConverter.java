package com.TP.converter;

import com.TP.DTO.SanPhamDTO;
import com.TP.entity.*;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class SanPhamConverter {
	public SanPhamDTO toDTO(SanPham sp) {
		SanPhamDTO spDTO= new SanPhamDTO();

		spDTO.setMasanpham(sp.getMasanpham());
		spDTO.setTensanpham(sp.getTensanpham());
		spDTO.setGiatien(sp.getGiatien());
		spDTO.setMota(sp.getMota());
		spDTO.setHinhsanpham(sp.getHinhsanpham());
		spDTO.setDanhcho(sp.getDanhcho());
		DanhMucSanPham danhMucSanPham = new DanhMucSanPham();
		danhMucSanPham.setMadanhmuc(sp.getDanhMucSanPham().getMadanhmuc());
		danhMucSanPham.setTendanhmuc(sp.getDanhMucSanPham().getTendanhmuc());
		spDTO.setDanhMucSanPham(danhMucSanPham);
		
		Set<ChiTietSanPham> chiTietSanPhams = new HashSet<ChiTietSanPham>();
		for (ChiTietSanPham value : sp.getChiTietSanPham())
		{
			ChiTietSanPham chitietSanpham = new ChiTietSanPham();
			chitietSanpham.setMachitietsanpham(value.getMachitietsanpham());
			
			MauSanPham mauSanPham = new MauSanPham();
			mauSanPham.setMamau(value.getMauSanPham().getMamau());
			mauSanPham.setTenmau(value.getMauSanPham().getTenmau());
			
			chitietSanpham.setMauSanPham(mauSanPham);
			
			SizeSanPham sizeSanPham = new SizeSanPham();
			sizeSanPham.setMasize(value.getSizeSanPham().getMasize());
			sizeSanPham.setSize(value.getSizeSanPham().getSize());
			
			chitietSanpham.setSizeSanPham(sizeSanPham);
			chitietSanpham.setSoluong(value.getSoluong());
			chiTietSanPhams.add(chitietSanpham);
		}
		spDTO.setChiTietSanPham(chiTietSanPhams);

		return spDTO;
	}
	public SanPham toEntity(SanPhamDTO sp)
	{
		SanPham entity= new SanPham();

		entity.setTensanpham(sp.getTensanpham());
		entity.setMasanpham(sp.getMasanpham());
		entity.setGiatien(sp.getGiatien());
		entity.setMota(sp.getMota());
		entity.setHinhsanpham(sp.getHinhsanpham());
		entity.setDanhcho(sp.getDanhcho());
		DanhMucSanPham danhMucSanPham = new DanhMucSanPham();
		danhMucSanPham.setMadanhmuc(sp.getDanhMucSanPham().getMadanhmuc());
		danhMucSanPham.setTendanhmuc(sp.getDanhMucSanPham().getTendanhmuc());
		entity.setDanhMucSanPham(danhMucSanPham);
		
		Set<ChiTietSanPham> chiTietSanPhams = new HashSet<ChiTietSanPham>();
		for (ChiTietSanPham value : sp.getChiTietSanPham())
		{
			ChiTietSanPham chitietSanpham = new ChiTietSanPham();
			chitietSanpham.setMachitietsanpham(value.getMachitietsanpham());
			
			MauSanPham mauSanPham = new MauSanPham();
			mauSanPham.setMamau(value.getMauSanPham().getMamau());
			mauSanPham.setTenmau(value.getMauSanPham().getTenmau());
			
			chitietSanpham.setMauSanPham(mauSanPham);
			
			SizeSanPham sizeSanPham = new SizeSanPham();
			sizeSanPham.setMasize(value.getSizeSanPham().getMasize());
			sizeSanPham.setSize(value.getSizeSanPham().getSize());
			
			chitietSanpham.setSizeSanPham(sizeSanPham);
			chitietSanpham.setSoluong(value.getSoluong());
			chiTietSanPhams.add(chitietSanpham);
		}
		entity.setChiTietSanPham(chiTietSanPhams);
		return entity;
		
	}
}
