package com.TP.DAO;

import com.TP.DTO.HoaDonDTO;
import com.TP.IService.IHoaDon;
import com.TP.converter.HoaDonConverter;
import com.TP.entity.HoaDon;
import com.TP.entity.UserEntity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class HoaDonDAO implements IHoaDon {

	@Autowired
	SessionFactory sessionFactory;
	@Autowired
	HoaDonConverter converter;

//	@Override
	public int save(HoaDon hoaDon) {

		Session session = sessionFactory.getCurrentSession();

		int id;
		if (hoaDon.getMahoadon() != 0) {

			/*
			 * id = (Integer) session.
			 * createQuery("UPDATE HOADON SET tinhtrang=:tinhtrang, ghichu=:ghichu WHERE mahoadon=:mahoadon"
			 * ) .setParameter("tinhtrang", hoaDon.getTinhtrang()) .setParameter("ghichu",
			 * hoaDon.getGhichu()) .setParameter("mahoadon", hoaDon.getMahoadon())
			 * .executeUpdate();
			 */
			HoaDon hd = session.get(HoaDon.class, hoaDon.getMahoadon());
			if (hoaDon.getTinhtrang() != null) {
				hd.setTinhtrang(hoaDon.getTinhtrang());
				hd.setThanhtoan(hoaDon.getThanhtoan());
			}
			if (hoaDon.getThanhtoan() != null) {
				hd.setThanhtoan(hoaDon.getThanhtoan());
			}
			if (hoaDon.getGhichu() != null) {
				hd.setGhichu(hoaDon.getGhichu());
			}

			session.update(hd);
			id = 1;
		} else {
			id = (Integer) session.save(hoaDon);
		}

		if (0 < id) {
			return id;
		} else {
			return 0;
		}
	}

	@Override
	public List<HoaDonDTO> findAll(int offset, int limit) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		List<HoaDonDTO> models = new ArrayList<HoaDonDTO>();
		List<HoaDon> listHoaDon = new ArrayList<HoaDon>();
		String query = "from HOADON";
		if (offset < 0) {
			listHoaDon = session.createQuery(query).getResultList();
		} else {
			listHoaDon = session.createQuery(query).setFirstResult(offset).setMaxResults(limit).getResultList();
		}

		for (HoaDon item : listHoaDon) {
			HoaDonDTO hdDTO = converter.toDTO(item);
			models.add(hdDTO);
		}

		return models;
	}

	@Override
	public List<HoaDonDTO> findAllByUserId(int userId, int offset, int limit) {
		Session session = sessionFactory.getCurrentSession();
		UserEntity userEntity=session.get(UserEntity.class,userId);
		List<HoaDonDTO> models = new ArrayList<HoaDonDTO>();
		List<HoaDon> listHoaDon = new ArrayList<HoaDon>();
		String query = "FROM HOADON " + "WHERE tenkhachhang='" + userEntity.getFullName() + "'";
		if (offset < 0) {
			listHoaDon = session.createQuery(query).getResultList();
		} else {
			listHoaDon = session.createQuery(query).setFirstResult(offset).setMaxResults(limit).getResultList();
		}

		for (HoaDon item : listHoaDon) {
			HoaDonDTO hdDTO = converter.toDTO(item);
			models.add(hdDTO);
		}

		return models;
	}

	@Override
	public HoaDonDTO findById(int id) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		return converter.toDTO(session.get(HoaDon.class, id));
	}

	@Override
	public boolean deleteById(int mahoadon) {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		HoaDon hoaDon = session.get(HoaDon.class, mahoadon);
		session.createQuery("delete CHITIETHOADON where mahoadon=:mahoadon").setParameter("mahoadon", mahoadon)
				.executeUpdate();

		/* Set<ChiTietHoaDon> chiTietHoaDons = hoaDon.getDanhsachChiTietHoaDon(); */
		/* for(ChiTietHoaDon chiTietHoaDon : chiTietHoaDons) { */
		/* } */
		session.createQuery("delete HOADON where mahoadon=:mahoadon").setParameter("mahoadon", mahoadon)
				.executeUpdate();
		return true;
	}

	@Override
	public long getBillsuccess() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String query = "SELECT COUNT(*) FROM HOADON where tinhtrang=1";

		return (long) session.createQuery(query).list().get(0);
	}

	@Override
	public int getTotalrevenue() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		/*
		 * String query= "SELECT SUM(REPLACE(tongtien, '.', '')) FROM HOADON";
		 * session.createQuery(query).getSingleResult(); return 0;
		 */
		Double sum = (Double) session
				.createNativeQuery("SELECT SUM(REPLACE(tongtien, '.', '')) FROM HOADON WHERE tinhtrang=1")
				.getSingleResult();
		if (sum != null) {
			return sum.intValue();
		}
		return 0;

	}

	@Override
	public long getBillprocessing() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		String query = "SELECT COUNT(*) FROM HOADON where tinhtrang!=1";

		return (long) session.createQuery(query).list().get(0);
	}

	@Override
	public Map<Integer, Double> getMonthlyrevenue() {
		// TODO Auto-generated method stub
		Map<Integer, Double> result = new HashMap<Integer, Double>();

//		
		Session session = sessionFactory.getCurrentSession();

		String query = "SELECT MONTH (createdDate) as month, SUM(REPLACE(tongtien, '.', '')) as total \n"
				+ " FROM HOADON\n" + " where tinhtrang=1 \n" 
				+ "group by  MONTH(createdDate)\n"
				+ "ORDER BY month ASC";
		List<Object[]>  rows = session.createNativeQuery(query).getResultList();

		for (Object[] aRow : rows)
		{
		    result.put( (Integer) aRow[0], (Double) aRow[1]);
		}
		
		return result;
	}

	@Override
	public Map<String, BigInteger> getProductSellbyCat() {
		// TODO Auto-generated method stub
		Session session = sessionFactory.getCurrentSession();
		Map<String,BigInteger> result = new HashMap<String, BigInteger>();
		String query="SELECT t.tendanhmuc,count(t.soluong) as soluong FROM\n" + 
				" (SELECT sp.madanhmuc,dm.tendanhmuc , cthd.soluong FROM  ( ( (dbminishop.chitiethoadon  cthd inner join dbminishop.hoadon hd)   inner join dbminishop.chitietsanpham ctsp  )\n" + 
				"															inner join dbminishop.sanpham sp  )  inner join dbminishop.danhmucsanpham dm\n" + 
				"where cthd.machitietsanpham=ctsp.machitietsanpham and ctsp.masanpham = sp.masanpham and sp.madanhmuc = dm.madanhmuc and hd.mahoadon=cthd.mahoadon and hd.tinhtrang=1) as t \n" + 
				" group by t.madanhmuc "; 
		List<Object[]> rows= session.createNativeQuery(query).getResultList();
		for (Object[] row : rows)
		{
			result.put('"' + (String)row[0]+'"', (BigInteger)row[1]);
		}
		return result;
	}
	public boolean CheckBillUserDelivered(int masanpham,String tenkhachhang) {
		// TODO Auto-generated method stub
		String tenkh="\""+tenkhachhang+"\"";
		String tinhtrang="FIN";
		 tinhtrang="\""+tinhtrang+"\"";

		Session session = sessionFactory.getCurrentSession();
		String query= " SELECT hd.tenkhachhang FROM  dbminishop.chitiethoadon  cthd inner join dbminishop.hoadon hd inner join chitietsanpham ctsp " +
				"where ctsp.machitietsanpham=cthd.machitietsanpham and ctsp.masanpham="+masanpham+" and hd.tenkhachhang="+tenkh+" and hd.mahoadon=cthd.mahoadon and hd.tinhtrang="+tinhtrang+"";
		List<Object[]> rows= session.createNativeQuery(query).getResultList();
		return rows.size() > 0;
	}

}
