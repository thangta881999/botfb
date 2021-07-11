package com.TP.DAO;

import com.TP.IService.IMauSanPham;
import com.TP.entity.MauSanPham;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
@Scope(proxyMode= ScopedProxyMode.TARGET_CLASS)
public class MauSanPhamDAO implements IMauSanPham {
	@Autowired
	SessionFactory sessionFactory;
	
	@Transactional
	public List<MauSanPham> LayDanhSachMau() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String query="from MAUSANPHAM";
		List<MauSanPham> mauSanPhams=(List<MauSanPham>)session.createQuery(query).getResultList();
		
		return mauSanPhams;
	}

	@Override
	@Transactional
	public int save(MauSanPham mauSanPham) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String query="FROM MAUSANPHAM " + 
				"WHERE tenmau='"+mauSanPham.getTenmau()+"'";
		List<MauSanPham> mauSanPhams = session.createQuery(query).getResultList();
		
		 
		if (mauSanPhams.size()>0)
		{
			return mauSanPhams.get(0).getMamau();
		}
		return (int) session.save(mauSanPham);
	
	
	}
	

}
