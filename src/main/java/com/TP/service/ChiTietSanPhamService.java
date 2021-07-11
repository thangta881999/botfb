package com.TP.service;

import com.TP.DAO.ChiTietSanPhamDAO;
import com.TP.IService.IChiTietSanPham;
import com.TP.entity.ChiTietSanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChiTietSanPhamService implements IChiTietSanPham {

	@Autowired
	ChiTietSanPhamDAO ctspDAO;
	
	@Override
	public int save(ChiTietSanPham chiTietSanPham) {
		// TODO Auto-generated method stub
		return ctspDAO.save(chiTietSanPham);
	}

	@Override
	public ChiTietSanPham findById(int machitietsp) {
		// TODO Auto-generated method stub
		return ctspDAO.findById(machitietsp);
	}

}
