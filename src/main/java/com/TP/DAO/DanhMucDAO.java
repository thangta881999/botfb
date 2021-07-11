package com.TP.DAO;

import com.TP.DTO.DanhMucDTO;
import com.TP.converter.DanhMucConverter;
import com.TP.entity.ChiTietSanPham;
import com.TP.entity.DanhMucSanPham;
import com.TP.entity.SanPham;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class DanhMucDAO{

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	DanhMucConverter converter;

	@Transactional
	public List<DanhMucSanPham> findAll(int offset, int limit) {
		Session session = sessionFactory.getCurrentSession();
		String query = "from DANHMUCSANPHAM";
		List<DanhMucSanPham> danhMucSanPhams = new ArrayList<DanhMucSanPham>();
		
		if (offset<0)
		{
			danhMucSanPhams = session.createQuery(query).getResultList();
		}
		else
		{
			danhMucSanPhams  = session.createQuery(query).setFirstResult(offset).setMaxResults(limit).getResultList();
		}
		

		return danhMucSanPhams;
	}
	@Transactional
	public List<DanhMucSanPham> findByParent()
	{
		Session session = sessionFactory.getCurrentSession();
		String query = "from DANHMUCSANPHAM WHERE parent_madanhmuc=0";
		List<DanhMucSanPham> danhMucSanPhams = new ArrayList<DanhMucSanPham>();
		
			danhMucSanPhams = session.createQuery(query).getResultList();
			return danhMucSanPhams;
		
	}
	@Transactional
	public List<DanhMucSanPham> findByChildren(int parent_madanhmuc) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String query = "from DANHMUCSANPHAM WHERE parent_madanhmuc='"+parent_madanhmuc+"'";
		List<DanhMucSanPham> danhMucSanPhams = new ArrayList<DanhMucSanPham>();
		
			danhMucSanPhams = session.createQuery(query).getResultList();
			return danhMucSanPhams;

	}
	@Transactional
	public boolean deleteAll(int madm) {
		Session session = sessionFactory.getCurrentSession();
		DanhMucSanPham danhmuc=session.get(DanhMucSanPham.class, madm);
		if (danhmuc.getParent_madanhmuc()==0)
		{
			List<DanhMucSanPham> danhMucSanPhams=findByChildren(madm);
			danhMucSanPhams.forEach(item->deleteChildrent(item.getMadanhmuc()));
		}
		
			
		return deleteChildrent(madm);
	}
	@Transactional
	public boolean deleteChildrent(int madm) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		DanhMucSanPham danhmuc=session.get(DanhMucSanPham.class, madm);
		Set<SanPham> SanPhams= danhmuc.getDanhsachsanpham();
		for(SanPham sp :SanPhams)
		{
			Set<ChiTietSanPham> chiTietSanPhams= sp.getChiTietSanPham();
			for(ChiTietSanPham chiTietSanPham :chiTietSanPhams)
			{
				session.createQuery("delete CHITIETHOADON WHERE machitietsanpham="+chiTietSanPham.getMachitietsanpham()).executeUpdate();
				
			}
			
			/*
			 * session.createQuery("delete chitietkhuyenmai WHERE masanpham="+masp).
			 * executeUpdate();
			 */
			 
			session.createQuery("delete CHITIETSANPHAM WHERE masanpham="+sp.getMasanpham()).executeUpdate();
			session.createQuery("delete SANPHAM WHERE masanpham="+sp.getMasanpham()).executeUpdate();
		}
		
		
		session.createQuery("delete DANHMUCSANPHAM WHERE madanhmuc="+madm).executeUpdate();
		return true;
	}

	@Transactional
	public DanhMucDTO findById(int madm) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		DanhMucSanPham danhmuc=session.get(DanhMucSanPham.class, madm);
			return converter.toDTO(danhmuc);
	}
	@Transactional
	public boolean save(DanhMucSanPham danhmuc) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		if (danhmuc.getMadanhmuc()!=0)
		{
			session.createQuery("UPDATE DANHMUCSANPHAM SET tendanhmuc='"+danhmuc.getTendanhmuc()+ "',parent_madanhmuc='"+danhmuc.getParent_madanhmuc()+"'WHERE madanhmuc="+danhmuc.getMadanhmuc()).executeUpdate();
			/* session.update(danhmuc); */
		}
		else {
			int id= (Integer) session.save(danhmuc);
		}
		return true;
	}
	@Transactional
	public int save2(DanhMucSanPham danhmuc) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String query="FROM DANHMUCSANPHAM " + 
				"WHERE tendanhmuc='"+danhmuc.getTendanhmuc()+"'";
		List<DanhMucSanPham> danhMucSanPhams = session.createQuery(query).getResultList();
		
		 
		if (danhMucSanPhams.size()>0)
		{
			return danhMucSanPhams.get(0).getMadanhmuc();
		}
		return (int) session.save(danhmuc);
	}
	


}
