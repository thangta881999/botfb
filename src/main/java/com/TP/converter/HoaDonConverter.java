package com.TP.converter;

import com.TP.DTO.HoaDonDTO;
import com.TP.entity.ChiTietHoaDon;
import com.TP.entity.HoaDon;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class HoaDonConverter {
	public HoaDon toEntity(HoaDonDTO dto)
	{
		HoaDon entity = new HoaDon();
		entity.setMahoadon(dto.getMahoadon());
		entity.setTenkhachhang(dto.getTenkhachhang());
		entity.setSodt(dto.getSodt());
		entity.setHinhthucgiaohang(dto.getHinhthucgiaohang());
		entity.setDiachigiaohang(dto.getDiachigiaohang());
		entity.setGhichu(dto.getGhichu());
		entity.setTinhtrang(dto.getTinhtrang());
		entity.setThanhtoan(dto.getThanhtoan());
		 entity.setTongtien(dto.getTongtien()); 
		 entity.setCreatedDate(dto.getCreatedDate());
		 entity.setUpdatedDate(dto.getUpdatedDate());
		Set<ChiTietHoaDon> chiTietHoaDons = new HashSet<ChiTietHoaDon>();
		for (ChiTietHoaDon value : dto.getDanhsachChiTietHoaDon())
		{
			ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
			chiTietHoaDon.setChiTietHoaDonId(value.getChiTietHoaDonId());
			chiTietHoaDon.setSoluong(value.getSoluong());
			chiTietHoaDon.setGiatien(value.getGiatien());
			
			chiTietHoaDons.add(chiTietHoaDon);
		}
			
		
		entity.setDanhsachChiTietHoaDon(chiTietHoaDons);
		return entity;
	}
	public HoaDonDTO toDTO (HoaDon entity)
	{
		HoaDonDTO dto = new HoaDonDTO();
		dto.setMahoadon(entity.getMahoadon());
		dto.setTenkhachhang(entity.getTenkhachhang());
		dto.setSodt(entity.getSodt());
		dto.setHinhthucgiaohang(entity.getHinhthucgiaohang());
		dto.setDiachigiaohang(entity.getDiachigiaohang());
		dto.setGhichu(entity.getGhichu());
		dto.setTinhtrang(entity.getTinhtrang());
		dto.setThanhtoan(entity.getThanhtoan());
		 dto.setTongtien(entity.getTongtien()); 
		 dto.setCreatedDate(entity.getCreatedDate());
		 dto.setUpdatedDate(entity.getUpdatedDate());
		Set<ChiTietHoaDon> chiTietHoaDons = new HashSet<ChiTietHoaDon>();
		for (ChiTietHoaDon value : entity.getDanhsachChiTietHoaDon())
		{
			ChiTietHoaDon chiTietHoaDon = new ChiTietHoaDon();
			chiTietHoaDon.setChiTietHoaDonId(value.getChiTietHoaDonId());
			chiTietHoaDon.setSoluong(value.getSoluong());
			chiTietHoaDon.setGiatien(value.getGiatien());
			
			chiTietHoaDons.add(chiTietHoaDon);
		}
			
		
		dto.setDanhsachChiTietHoaDon(chiTietHoaDons);
		return dto;
	}
}
