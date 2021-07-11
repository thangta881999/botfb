package com.TP.service;

import com.TP.DAO.HoaDonDAO;
import com.TP.DTO.HoaDonDTO;
import com.TP.IService.IHoaDon;
import com.TP.entity.HoaDon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.util.List;
import java.util.Map;

@Service
public class HoaDonService implements IHoaDon {

    @Autowired
    HoaDonDAO hoaDonDAO;


    @Override
    public List<HoaDonDTO> findAll(int offset, int limit) {
        // TODO Auto-generated method stub
        return hoaDonDAO.findAll(offset, limit);
    }

    @Override
    public List<HoaDonDTO> findAllByUserId(int userId, int offset, int limit) {
        return hoaDonDAO.findAllByUserId(userId,offset,limit);
    }

    @Override
    public int save(HoaDon hoaDon) {

        // TODO Auto-generated method stub
        return hoaDonDAO.save(hoaDon);
    }

    @Override
    public boolean CheckBillUserDelivered(int masanpham, String tenkhachhang) {
        return hoaDonDAO.CheckBillUserDelivered(masanpham, tenkhachhang);
    }

    @Override
    public HoaDonDTO findById(int id) {
        // TODO Auto-generated method stub
        return hoaDonDAO.findById(id);
    }

    @Override
    public boolean deleteById(int mahoadon) {
        // TODO Auto-generated method stub
        return hoaDonDAO.deleteById(mahoadon);
    }

    @Override
    public long getBillsuccess() {
        // TODO Auto-generated method stub
        return hoaDonDAO.getBillsuccess();
    }

    @Override
    public int getTotalrevenue() {
        // TODO Auto-generated method stub
        return hoaDonDAO.getTotalrevenue();
    }

    @Override
    public long getBillprocessing() {
        // TODO Auto-generated method stub
        return hoaDonDAO.getBillprocessing();
    }

    @Override
    public Map<Integer, Double> getMonthlyrevenue() {
        // TODO Auto-generated method stub
        return hoaDonDAO.getMonthlyrevenue();
    }

    @Override
    public Map<String, BigInteger> getProductSellbyCat() {
        // TODO Auto-generated method stub
        return hoaDonDAO.getProductSellbyCat();
    }

}
