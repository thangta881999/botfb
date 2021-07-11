package com.TP.IService;

import com.TP.entity.RecommendRating;

public interface IRecommendRatingService {
    public boolean checkExistUser(int userId);

    public RecommendRating save(RecommendRating recommendRating);

    public RecommendRating getById(int user_id);

    public void removeAll();
}
