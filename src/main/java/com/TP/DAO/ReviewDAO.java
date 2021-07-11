package com.TP.DAO;

import com.TP.DTO.RecommendSystem.AVGRatedProductDTO;
import com.TP.IService.IReviewService;
import com.TP.entity.Review;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class ReviewDAO implements IReviewService {
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Long save(Review review) {
        Session session = sessionFactory.getCurrentSession();
        return (Long) session.save(review);
    }

    @Override
    public List<Review> findByroductId(int id) {
        Session session = sessionFactory.getCurrentSession();
    
        String query = "FROM REVIEW " + "WHERE masanpham='" + id+ "'";

        return session.createQuery(query).getResultList();
    }

    @Override
    public Double averageRatingByProductId(int id) {
        Session session = sessionFactory.getCurrentSession();
        String query = "SELECT avg(r.rating) FROM REVIEW r  " + "WHERE r.sanPham.masanpham='" + id+ "'";
        return (Double) session.createQuery(query).getSingleResult();
    }

    @Override
    public List<Integer> findUsersReview() {
        Session session = sessionFactory.getCurrentSession();
        String query = "SELECT DISTINCT r.user.id from REVIEW r order by r.user.id asc";

        return session.createQuery(query).getResultList();
    }

    @Override
    public List<Integer> findProductsReview() {
        Session session = sessionFactory.getCurrentSession();
        String query = "SELECT distinct r.sanPham.masanpham from REVIEW r order by r.sanPham.masanpham asc";

        return session.createQuery(query).getResultList();
    }

    @Override
    public List<Review> findAllByOrderByProductAsc() {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM REVIEW ORDER BY masanpham ASC" ;
        return session.createQuery(query).getResultList();
    }

    @Override
    public List<AVGRatedProductDTO> avgRatedProduct() {
        Session session = sessionFactory.getCurrentSession();
        String query = "SELECT r.sanPham.masanpham, avg(r.rating) FROM REVIEW r  GROUP BY r.sanPham.masanpham ORDER BY r.sanPham.masanpham ASC" ;
        List<Object[]> rows= session.createQuery(query).getResultList();
        List<AVGRatedProductDTO> avgRatedProductDTOS= new ArrayList<>();
        for (Object[] row : rows)
        {
            AVGRatedProductDTO avgRatedProductDTO = new AVGRatedProductDTO((Integer) row[0],(Double) row[1]);
            avgRatedProductDTOS.add(avgRatedProductDTO);
        }
        return avgRatedProductDTOS;
    }

    @Override
    public int checkUserIsRated(int userId) {
        Session session = sessionFactory.getCurrentSession();
        String query = "SELECT DISTINCT r.user.id from REVIEW r where r.user.id='"+userId +"'";
        return session.createQuery(query).getResultList().size();
    }
}
