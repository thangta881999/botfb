package com.TP.service;

import com.TP.DAO.MauSanPhamDAO;
import com.TP.IService.IMauSanPham;
import com.TP.entity.MauSanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MauSanPhamService implements IMauSanPham {
	@Autowired
	MauSanPhamDAO mauSanPhamDAO;

	public List<MauSanPham> LayDanhSachMau() {
		
		return mauSanPhamDAO.LayDanhSachMau();
	}

	@Override
	public int save(MauSanPham mauSanPham) {
		// TODO Auto-generated method stub
		return mauSanPhamDAO.save(mauSanPham);
	}
	
	

}
