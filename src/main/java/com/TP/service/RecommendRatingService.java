package com.TP.service;

import com.TP.DAO.RecommendRatingDAO;
import com.TP.IService.IRecommendRatingService;
import com.TP.entity.RecommendRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecommendRatingService implements IRecommendRatingService {
    @Autowired
    private RecommendRatingDAO recommendRatingDAO;

    @Override
    public boolean checkExistUser(int userId) {
        return recommendRatingDAO.checkExistUser(userId);
    }

    @Override
    public RecommendRating save(RecommendRating recommendRating) {
        return recommendRatingDAO.save(recommendRating);
    }

    @Override
    public RecommendRating getById(int user_id) {
        return recommendRatingDAO.getById(user_id);
    }

    @Override
    public void removeAll() {
        recommendRatingDAO.removeAll();
    }
}
