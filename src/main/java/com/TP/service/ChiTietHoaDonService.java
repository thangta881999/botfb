package com.TP.service;

import com.TP.DAO.ChiTietHoaDonDAO;
import com.TP.IService.IChiTietHoaDon;
import com.TP.entity.ChiTietHoaDon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ChiTietHoaDonService implements IChiTietHoaDon{

	@Autowired
	ChiTietHoaDonDAO chiTietHoaDonDAO;
	
	public boolean ThemChiTietHoaDon(ChiTietHoaDon chiTietHoaDon) {
		return chiTietHoaDonDAO.ThemChiTietHoaDon(chiTietHoaDon);
	}

}
