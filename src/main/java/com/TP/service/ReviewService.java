package com.TP.service;

import com.TP.DAO.ReviewDAO;
import com.TP.DTO.RecommendSystem.AVGRatedProductDTO;
import com.TP.IService.IReviewService;
import com.TP.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReviewService implements IReviewService {
    @Autowired
    private ReviewDAO reviewDAO;
    @Override
    public Long save(Review review) {
        return reviewDAO.save(review);
    }

    @Override
    public List<Review> findByroductId(int id) {
        return reviewDAO.findByroductId(id);
    }

    @Override
    public Double averageRatingByProductId(int id) {
        return reviewDAO.averageRatingByProductId(id);
    }

    @Override
    public List<Integer> findUsersReview() {
        return reviewDAO.findUsersReview();
    }

    @Override
    public List<Integer> findProductsReview() {
        return reviewDAO.findProductsReview();
    }

    @Override
    public List<Review> findAllByOrderByProductAsc() {
        return reviewDAO.findAllByOrderByProductAsc();
    }

    @Override
    public List<AVGRatedProductDTO> avgRatedProduct() {
        return reviewDAO.avgRatedProduct();
    }

    @Override
    public int checkUserIsRated(int userid) {
        return reviewDAO.checkUserIsRated(userid);
    }
}
