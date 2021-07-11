package com.TP.IService;

import com.TP.DTO.HoaDonDTO;
import com.TP.entity.HoaDon;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

public interface IHoaDon {
	List<HoaDonDTO> findAll(int offset, int limit);
	List<HoaDonDTO> findAllByUserId(int userId,int offset, int limit);
	int save(HoaDon hoaDon);
	boolean CheckBillUserDelivered(int massanpham,String tenkhachhang);
	HoaDonDTO findById(int id);
	long getBillsuccess();
	long getBillprocessing();
	int getTotalrevenue();
	boolean deleteById(int mahoadon);
	Map<Integer,Double> getMonthlyrevenue ();
	Map<String,BigInteger> getProductSellbyCat();
}
