package com.TP.service;

import com.TP.DAO.SizeSanPhamDAO;
import com.TP.IService.ISizeSanPham;
import com.TP.entity.SizeSanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SizeSanPhamService implements ISizeSanPham {
	@Autowired
	SizeSanPhamDAO sizeSanPhamDAO;
	public List<SizeSanPham> layDanhSachSize() {
		// TODO Auto-generated method stub
		return sizeSanPhamDAO.layDanhSachSize();
	}
	@Override
	public int save(SizeSanPham sizeSanPham) {
		// TODO Auto-generated method stub
		return sizeSanPhamDAO.save(sizeSanPham);
	}
	
}
