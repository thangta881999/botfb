package com.TP.converter;

import com.TP.DTO.RecommendSystem.RatingRSDTO;
import com.TP.entity.Review;
import org.springframework.stereotype.Component;

@Component
public class ReviewConverter {
    public RatingRSDTO reviewToRatingRSDTO(Review review)
    {
        RatingRSDTO ratingRSDTO=new RatingRSDTO();
        ratingRSDTO.setProductId(review.getSanPham().getMasanpham());
        ratingRSDTO.setUserId(review.getUser().getId());
        ratingRSDTO.setValue(review.getRating());
        return ratingRSDTO;
    }
}
