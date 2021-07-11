package com.TP.DAO;

import com.TP.IService.IChiTietSanPham;
import com.TP.entity.ChiTietSanPham;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ChiTietSanPhamDAO implements IChiTietSanPham  {


	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public int save(ChiTietSanPham chiTietSanPham) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();

		int id;
		if (chiTietSanPham.getMachitietsanpham() != 0) {

			
			ChiTietSanPham ctsp = session.get(ChiTietSanPham.class, chiTietSanPham.getMachitietsanpham());
			
			
			if (chiTietSanPham.getSoluong() >0) {
				int soluong= ctsp.getSoluong()-chiTietSanPham.getSoluong();
				ctsp.setSoluong(soluong);
			}

			session.update(ctsp);
			id = 1;
		} else {
			id = (Integer) session.save(chiTietSanPham);
		}

		if (0 < id) {
			return id;
		} else {
			return 0;
		}
	}

	@Override
	public ChiTietSanPham findById(int machitietsp) {
		// TODO Auto-generated method stub
		Session session= sessionFactory.getCurrentSession();
		return session.get(ChiTietSanPham.class, machitietsp) ;
	}

}
