package com.TP.IService;

import com.TP.DTO.RecommendSystem.AVGRatedProductDTO;
import com.TP.entity.Review;

import java.util.List;

public interface IReviewService {
    Long save(Review review);
    List<Review> findByroductId(int id);
    Double averageRatingByProductId (int id);
    List<Integer> findUsersReview();
    List<Integer> findProductsReview();
    List<Review> findAllByOrderByProductAsc();
    List<AVGRatedProductDTO> avgRatedProduct();
    int checkUserIsRated(int userid);
}
