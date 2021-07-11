package com.TP.api.web;

import com.TP.DTO.RecommendSystem.AVGRatedProductDTO;
import com.TP.DTO.RecommendSystem.RatingRSDTO;
import com.TP.IService.*;
import com.TP.Respone.ValidRespone;
import com.TP.converter.ReviewConverter;
import com.TP.entity.CosineSimilarity;
import com.TP.entity.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("api/reviews")
public class ReviewAPI {
    @Autowired
    private IReviewService reviewService;

    @Autowired
    private ISanPham sanPhamService;
    @Autowired
    private IHoaDon hoaDonService;
    @Autowired
    private IUser userService;

    @Autowired
    private ReviewConverter reviewConverter;

    @Autowired
    private ICosineSimilarityService cosineSimilarityService;

    @Autowired
    private IRecommendRatingService recommendRatingService;

    @PostMapping
    public ValidRespone ratingProduct(@Valid @RequestBody Review dataJson, BindingResult result) {
        ValidRespone response = new ValidRespone();
        if (result.hasErrors()) {
            Map<String, String> errors = result.getFieldErrors().stream()
                    .collect(
                            Collectors.toMap(FieldError::getField, FieldError::getDefaultMessage)
                    );

            response.setValidated(false);
            response.setErrorMessages(errors);
        } else {
            response.setValidated(true);
            reviewService.save(dataJson);
            new Thread(new Runnable() {
                @Override
                public void run() {
                    createCosineSimilarity();
                }
            }).start();
        }
        return response;
    }

    @PostMapping("/recommend/create-cosine-similarity")
    public ResponseEntity<String> createCosineSimilarity() {
        List<Integer> list_users = reviewService.findUsersReview();
        List<Integer> list_product = reviewService.findProductsReview();

        List<RatingRSDTO> listRatingRS = reviewService.findAllByOrderByProductAsc()
                .stream()
                .map(value -> reviewConverter.reviewToRatingRSDTO(value))
                .collect(Collectors.toList());

        List<RatingRSDTO> listRatingNormalized = listRatingRS.stream().map(rt -> new RatingRSDTO(rt.getUserId(), rt.getProductId(), rt.getValue())).collect(Collectors.toList());

        List<AVGRatedProductDTO> listAVG = reviewService.avgRatedProduct();

        int size_list_avg = listAVG.size();
        int size_list_user = list_users.size();
        for (int i = 0; i < size_list_avg; i++) {
            int pd_id = list_product.get(i);
            double avg_pd = listAVG.get(i).getAvgRated();
            listRatingNormalized.parallelStream().filter(value -> value.getProductId() == pd_id).forEach(value -> {
                value.setValue(value.getValue() - avg_pd);
            });
        }

        List<CosineSimilarity> cosSimilarities = new ArrayList<>();

        // calc cosine similarity
        for (int i = size_list_avg - 1; i > 0; i--) {
            int product_id1 = list_product.get(i);
            List<RatingRSDTO> user_rated_product1 = calcUserRatedProduct(listRatingNormalized, product_id1);
            for (int j = i - 1; j >= 0; j--) {
                int product_id2 = list_product.get(j);
                List<RatingRSDTO> user_rated_product2 = calcUserRatedProduct(listRatingNormalized, product_id2);

                double pd1_dot_pd2 = p1_dot_p2(user_rated_product1, user_rated_product2);
                double norm_pd1 = calc_norm(user_rated_product1);
                double norm_pd2 = calc_norm(user_rated_product2);
                double norm_pd1_mul_norm_pd2 = norm_pd1 * norm_pd2;
//
                double cosineSimilarity = norm_pd1_mul_norm_pd2 != 0.0 ? pd1_dot_pd2 / norm_pd1_mul_norm_pd2 : -1;
//
                cosineSimilarity=(double) Math.round(cosineSimilarity*100)/100;
                CosineSimilarity a = new CosineSimilarity(product_id1, product_id2, cosineSimilarity);
                cosSimilarities.add(a);
                System.out.println("i: " + i + " - j: " + j);
            }
        }

        cosineSimilarityService.removeAll();
        cosineSimilarityService.saveAll(cosSimilarities);
        recommendRatingService.removeAll();
        return ResponseEntity.ok().body("done");
    }

    private List<RatingRSDTO> calcUserRatedProduct(List<RatingRSDTO> list, int pd) {
        List<RatingRSDTO> a = new ArrayList<>();
        int size_list = list.size();
        for (int i = 0; i < size_list; i++) {
            if (list.get(i).getProductId() == pd) {
                a.add(list.get(i));
            }
        }
        return a;
    }

    private double p1_dot_p2(List<RatingRSDTO> list1, List<RatingRSDTO> list2) {
        int size_list1 = list1.size();
        int size_list2 = list2.size();
        double p1_dot_p2 = 0.0;
        for (int i = 0; i < size_list1; i++) {
            for (int j = 0; j < size_list2; j++) {
                RatingRSDTO rt1 = list1.get(i);
                RatingRSDTO rt2 = list2.get(j);
                if (rt1.getUserId() == rt2.getUserId()) {
                    p1_dot_p2 += (rt1.getValue() * rt2.getValue());
                    break;
                }
            }
        }
        return p1_dot_p2;
    }

    private double calc_norm(List<RatingRSDTO> list) {
        int size_list = list.size();
        double norm = 0.0;
        for (int i = 0; i < size_list; i++) {
            RatingRSDTO rt = list.get(i);
            if (rt.getValue() != 0.0) {
                norm += Math.pow(rt.getValue(), 2);
            }
        }
        return Math.sqrt(norm);
    }

}

