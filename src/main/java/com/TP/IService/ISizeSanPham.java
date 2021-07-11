package com.TP.IService;

import com.TP.entity.SizeSanPham;

import java.util.List;

public interface ISizeSanPham {
	List<SizeSanPham> layDanhSachSize();
	int save(SizeSanPham sizeSanPham);
}
