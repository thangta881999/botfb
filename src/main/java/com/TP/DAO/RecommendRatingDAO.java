package com.TP.DAO;

import com.TP.entity.RecommendRating;
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
public class RecommendRatingDAO  {
    @Autowired
    private SessionFactory sessionFactory;

    public boolean checkExistUser(int userId) {
        Session session = sessionFactory.getCurrentSession();

//        String query = "FROM RECOMMENDRATING r " + "WHERE user_id='" + userId+ "'";
        RecommendRating recommendRating=new RecommendRating();
//         recommendRating= (RecommendRating) session.createQuery(query).getSingleResult();
         recommendRating=session.get(RecommendRating.class,userId);
        if(recommendRating!=null)
        {
            return true;
        }
        return false;
    }

    public RecommendRating save(RecommendRating recommendRating) {
        Session session = sessionFactory.getCurrentSession();
        session.saveOrUpdate(recommendRating);
        return  recommendRating;
    }

    public RecommendRating getById(int user_id) {
        Session session = sessionFactory.getCurrentSession();
        return (RecommendRating) session.get(RecommendRating.class,user_id);
    }

    public void removeAll() {
        Session session = sessionFactory.getCurrentSession();
        String query = "DELETE FROM RECOMMENDRATING" ;
        session.createQuery(query).executeUpdate();


    }
}
