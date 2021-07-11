package com.TP.DAO;

import com.TP.entity.CosineSimilarity;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
@Scope(proxyMode = ScopedProxyMode.TARGET_CLASS)
@Transactional
public class CosineSimilarityDAO {
    @Autowired
    SessionFactory sessionFactory;

    public CosineSimilarity findByColumnAndRow(int column, int row) {
        Session session = sessionFactory.getCurrentSession();
        String query="from COSINESIMILARITY where row_product_id='" + row + "' and column_product_id='" + column + "'";
        return (CosineSimilarity) session.createQuery(query).getSingleResult();
    }

    public List<CosineSimilarity> findAll() {
        Session session = sessionFactory.getCurrentSession();
        String query = "FROM COSINESIMILARITY " ;
        return session.createQuery(query).getResultList();
    }

    public void deleteAll() {
        Session session = sessionFactory.getCurrentSession();
        String query = "delete from COSINESIMILARITY " ;
        session.createQuery(query).executeUpdate();
    }

    public void saveAll(List<CosineSimilarity> list) {
        Session session = sessionFactory.getCurrentSession();
        for(CosineSimilarity cosineSimilarity :list)
        {
            session.save(cosineSimilarity);
        }
    }

    public CosineSimilarity save(CosineSimilarity cosineSimilarityDTO) {
        Session session = sessionFactory.getCurrentSession();
        return (CosineSimilarity) session.save(cosineSimilarityDTO);
    }
}
