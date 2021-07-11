package com.TP.DAO;

import com.TP.DTO.SanPhamDTO;
import com.TP.IService.ISanPham;
import com.TP.converter.SanPhamConverter;
import com.TP.entity.ChiTietSanPham;
import com.TP.entity.SanPham;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.search.FullTextSession;
import org.hibernate.search.Search;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class SanPhamDAO implements ISanPham {
	@Autowired
	SessionFactory sessionFactory;

	@Autowired
	SanPhamConverter sanphamConverter;

	@Override
	public boolean themSanPham(SanPham sanPham) {
		Session session = sessionFactory.getCurrentSession();
		int id = (Integer) session.save(sanPham);
		return true;
	}

	@Override
	public int save2(SanPham sanPham) {
		Session session = sessionFactory.getCurrentSession();
		String query = "FROM SANPHAM " + "WHERE tensanpham='" + sanPham.getTensanpham() + "'";
		List<SanPham> sanPhams = session.createQuery(query).getResultList();
		if (sanPhams.size() > 0) {
			return sanPhams.get(0).getMasanpham();
		}
		return (Integer) session.save(sanPham);

	}

	@Override
	public List<SanPhamDTO> findAll(int offset, int limit) {
		Session session = sessionFactory.getCurrentSession();
		List<SanPhamDTO> models = new ArrayList<SanPhamDTO>();
		List<SanPham> listSanPham = new ArrayList<SanPham>();
		String query = "from SANPHAM";
		if (offset < 0) {
			listSanPham = session.createQuery(query).getResultList();
		} else {
			listSanPham = session.createQuery(query).setFirstResult(offset).setMaxResults(limit).getResultList();
		}
		for (SanPham item : listSanPham) {
			SanPhamDTO spDTO = sanphamConverter.toDTO(item);
			models.add(spDTO);
		}
		return models;
	}

	@Override
	public SanPham LayDanhSachChiTietSanPhamTheoMa(int masanpham) {

		String query = "from SANPHAM sp where sp.masanpham=" + masanpham;

		Session session = sessionFactory.getCurrentSession();
		SanPham sanPham = (SanPham) session.createQuery(query).getSingleResult();
		return sanPham;
	}

	@Override
	public List<SanPhamDTO> getProductRecommend(Integer[] ids) {
		Session session = sessionFactory.getCurrentSession();
		Query query = session.createQuery("FROM SANPHAM sp WHERE sp.id IN :ids");
		query.setParameterList("ids", Arrays.asList(ids));
		 List<SanPham> sanPhams = query.getResultList();
		List<SanPhamDTO> models = new ArrayList<SanPhamDTO>();
		for (SanPham item : sanPhams) {
			SanPhamDTO spDTO = sanphamConverter.toDTO(item);
			models.add(spDTO);
		}

		return models;
	}

	@Override
	public List<SanPhamDTO> LayDanhSachSanPhamTheoMaDanhMuc(int madanhmuc, int offset, int limit) {

		String query = "from SANPHAM sp where sp.danhMucSanPham.madanhmuc=" + madanhmuc;

		Session session = sessionFactory.getCurrentSession();
		List<SanPham> listSanPham = new ArrayList<SanPham>();
		List<SanPhamDTO> models = new ArrayList<SanPhamDTO>();
		if (offset < 0) {
			listSanPham = session.createQuery(query).getResultList();
		} else {

			listSanPham = session.createQuery(query).setFirstResult(offset).setMaxResults(limit).getResultList();
		}
		for (SanPham item : listSanPham) {
			SanPhamDTO spDTO = sanphamConverter.toDTO(item);
			models.add(spDTO);
		}

		return models;
	}

//	@Override
	public boolean XoaSanPhamTheoMaSanPham(int masp) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		SanPham sanPham = session.get(SanPham.class, masp);
		Set<ChiTietSanPham> chiTietSanPhams = sanPham.getChiTietSanPham();
		for (ChiTietSanPham chiTietSanPham : chiTietSanPhams) {
			session.createQuery("delete CHITIETHOADON WHERE machitietsanpham=" + chiTietSanPham.getMachitietsanpham())
					.executeUpdate();

		}

		/*
		 * session.createQuery("delete chitietkhuyenmai WHERE masanpham="+masp).
		 * executeUpdate();
		 */

		session.createQuery("delete CHITIETSANPHAM WHERE masanpham=" + masp).executeUpdate();
		session.createQuery("delete SANPHAM WHERE masanpham=" + masp).executeUpdate();
		return true;
	}

	@Override
	public boolean capnhatSanPham(SanPham sanPham) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		session.update(sanPham);
		return true;

	}

	@Override
	public void indexBooks() throws Exception {
		// TODO Auto-generated method stub

		try {
			Session session = sessionFactory.getCurrentSession();
			FullTextSession fullTextSession = Search.getFullTextSession(session);
			fullTextSession.createIndexer().startAndWait();
		} catch (Exception e) {
			throw e;
		}

	}

	@Override
	public List<SanPhamDTO> search(String keyword, int offset, int limit) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();

		FullTextSession fullTextSession = Search.getFullTextSession(session);

		QueryBuilder qb = fullTextSession.getSearchFactory().buildQueryBuilder().forEntity(SanPham.class).get();

		org.apache.lucene.search.Query query = qb.keyword().onFields("tensanpham", "mota") // Chỉ định tìm theo cột nào
				.matching(keyword).createQuery();

		javax.persistence.Query hibQuery = fullTextSession.createFullTextQuery(query, SanPham.class);

		List<SanPham> results = new ArrayList<SanPham>();
		if (offset >= 0) {
			results = hibQuery.setFirstResult(offset).setMaxResults(limit).getResultList();
		} else

		{
			results = hibQuery.getResultList();
		}

		List<SanPhamDTO> dtos = new ArrayList<SanPhamDTO>();
		for (int i = 0; i < results.size(); i++) {
			dtos.add(sanphamConverter.toDTO(results.get(i)));
		}
		return dtos;

	}

	@Override
	public List<SanPhamDTO> findByCategoryAndOrder(int madanhmuc, String orderby, String order, int offset, int limit) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<SanPham> results = new ArrayList<SanPham>();

		if (offset < 0)
			results = session.createQuery(
					"FROM SANPHAM sp where sp.danhMucSanPham.madanhmuc=:madanhmuc ORDER BY " + orderby + " " + order)
					.setParameter("madanhmuc", madanhmuc).getResultList();
		else {
			results = session
					.createQuery("FROM SANPHAM sp where sp.danhMucSanPham.madanhmuc=:madanhmuc ORDER BY " + orderby
							+ " " + order)
					.setParameter("madanhmuc", madanhmuc).setFirstResult(offset).setMaxResults(limit).getResultList();
		}

		List<SanPhamDTO> dtos = new ArrayList<SanPhamDTO>();
		for (int i = 0; i < results.size(); i++) {
			dtos.add(sanphamConverter.toDTO(results.get(i)));
		}
		return dtos;
	}

	@Override
	public List<SanPhamDTO> sortAll(String orderby, String order, int offset, int limit) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<SanPham> results = new ArrayList<SanPham>();

		if (offset < 0)
			results = session.createQuery("FROM SANPHAM sp  ORDER BY " + orderby + " " + order).getResultList();
		else {
			results = session.createQuery("FROM SANPHAM sp  ORDER BY " + orderby + " " + order).setFirstResult(offset)
					.setMaxResults(limit).getResultList();
		}

		List<SanPhamDTO> dtos = new ArrayList<SanPhamDTO>();
		for (int i = 0; i < results.size(); i++) {
			dtos.add(sanphamConverter.toDTO(results.get(i)));
		}
		return dtos;
	}

}
