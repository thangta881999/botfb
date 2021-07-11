package com.TP.IService;

import com.TP.entity.ChiTietSanPham;

public interface IChiTietSanPham {
		int save(ChiTietSanPham chiTietSanPham);
		ChiTietSanPham findById(int machitietsp);
}
