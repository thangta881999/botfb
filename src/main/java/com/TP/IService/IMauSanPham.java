package com.TP.IService;

import com.TP.entity.MauSanPham;

import java.util.List;

public interface IMauSanPham {
	List<MauSanPham> LayDanhSachMau();
	int save(MauSanPham mauSanPham);
}
