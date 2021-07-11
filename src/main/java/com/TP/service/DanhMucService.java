package com.TP.service;

import com.TP.DAO.DanhMucDAO;
import com.TP.DTO.DanhMucDTO;
import com.TP.IService.IDanhMuc;
import com.TP.converter.DanhMucConverter;
import com.TP.entity.DanhMucSanPham;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DanhMucService implements IDanhMuc{

	@Autowired
	DanhMucDAO danhMucDAO;
	@Autowired
	DanhMucConverter danhMucConverter;
	public List<DanhMucSanPham> findAll(int offset, int limit) {
		return danhMucDAO.findAll(offset,limit);
		
	}

	@Override
	public boolean deleteChildrent(int madm) {
		// TODO Auto-generated method stub
		return danhMucDAO.deleteChildrent(madm);
	}

	@Override
	public DanhMucDTO findById(int madm) {
		// TODO Auto-generated method stub
		return danhMucDAO.findById(madm);
	}

	@Override
	public boolean save(DanhMucSanPham danhmuc) {
		// TODO Auto-generated method stub
		return danhMucDAO.save(danhmuc);
	}
	public int save2(DanhMucSanPham danhmuc) {
		// TODO Auto-generated method stub
		return danhMucDAO.save2(danhmuc);
	}

	@Override
	public List<DanhMucSanPham> findByParent() {
		// TODO Auto-generated method stub
		return danhMucDAO.findByParent();
	}

	@Override
	public List<DanhMucSanPham> findByChildren(int parent_madanhmuc) {
		// TODO Auto-generated method stub
		return danhMucDAO.findByChildren(parent_madanhmuc);
	}

	@Override
	public List<DanhMucDTO> CreateMenu() {
		// TODO Auto-generated method stub
		List<DanhMucDTO> danhMucDTOs = new ArrayList<DanhMucDTO>();
		//Lấy danh mục gốc
		List<DanhMucSanPham> danhMucSanPhams =findByParent();

		
		for(DanhMucSanPham danhMucSanPham:danhMucSanPhams)
		{
			DanhMucDTO danhMucDTO = danhMucConverter.toDTO(danhMucSanPham);
			
			List<DanhMucSanPham> danhMucChildrents=findByChildren(danhMucDTO.getMadanhmuc());
			List<DanhMucDTO> danhMucDTOChildrents= new ArrayList<DanhMucDTO>();
//			
			for(DanhMucSanPham danhMucChildrent: danhMucChildrents)
			{
				DanhMucDTO danhMucDTOChildrent =danhMucConverter.toDTO(danhMucChildrent);
			
				danhMucDTOChildrents.add(danhMucDTOChildrent);
			}
			danhMucDTO.setChildrentsDanhMuc(danhMucDTOChildrents);
			
			danhMucDTOs.add(danhMucDTO);
			
		}
		return danhMucDTOs;
	}

	@Override
	public boolean deleteAll(int madm) {
		// TODO Auto-generated method stub
		return danhMucDAO.deleteAll(madm);
	}

}
