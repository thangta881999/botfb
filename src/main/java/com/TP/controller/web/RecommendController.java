package com.TP.controller.web;

import com.TP.DTO.RecommendSystem.AVGRatedProductDTO;
import com.TP.DTO.RecommendSystem.RatingRSDTO;
import com.TP.DTO.SanPhamDTO;
import com.TP.IService.ICosineSimilarityService;
import com.TP.IService.IRecommendRatingService;
import com.TP.IService.IReviewService;
import com.TP.IService.ISanPham;
import com.TP.converter.ReviewConverter;
import com.TP.entity.CosineSimilarity;
import com.TP.entity.RecommendRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Controller
@RequestMapping("/recommend")
public class RecommendController {
    @Autowired
    private IRecommendRatingService recommendRatingService;
    @Autowired
    private ReviewConverter reviewConverter;
    @Autowired
    private ISanPham sanPhamService;
    @Autowired
    private IReviewService reviewService;
    @Autowired
    private ICosineSimilarityService cosineSimilarityService;

    @GetMapping
    public String Default(@RequestParam int userId, ModelMap modelMap) {
        RecommendRating recommendRating = new RecommendRating();
        List<SanPhamDTO> sanPhamDTOS= new ArrayList<>();
        if(recommendRatingService.checkExistUser(userId))
        {
             recommendRating=recommendRatingService.getById(userId);
        }
        else
        {
            if(!Objects.requireNonNull(recommend_product_for_user(userId).getBody()).getProducts().equals(""))
            {
                recommendRating=recommendRatingService.getById(userId);
            }
            else
            {
                recommendRating.setProducts("");
            }


        }
        if(!recommendRating.getProducts().equals(""))
        {
            Integer [] ids= Stream.of(recommendRating.getProducts().split("-")).map(Integer::valueOf).toArray(Integer[]::new);
            sanPhamDTOS=sanPhamService.getProductRecommend(ids);
        }

        modelMap.addAttribute("listSanPhams", sanPhamDTOS);

        return "web/recommend";
    }

    @GetMapping("/create-prediction-rating/{userId}")
    public ResponseEntity<RecommendRating> recommend_product_for_user(@PathVariable("userId") int user_id) {
        long startTime = new Date().getTime();

        if (!(reviewService.checkUserIsRated(user_id) > 0)) {
            return ResponseEntity.ok().body(new RecommendRating(user_id, ""));
        }


        List<Integer> list_users = reviewService.findUsersReview();
        List<Integer> list_product = reviewService.findProductsReview();

        List<RatingRSDTO> listRatingRS = reviewService.findAllByOrderByProductAsc().stream()
                .map(value -> reviewConverter.reviewToRatingRSDTO(value))
                .collect(Collectors.toList());

        List<RatingRSDTO> listRatingNormalized = listRatingRS.stream().map(rt -> new RatingRSDTO(rt.getUserId(), rt.getProductId(), rt.getValue())).collect(Collectors.toList());

        List<AVGRatedProductDTO> listAVG = reviewService.avgRatedProduct();

        // normalized utility matrix
        System.out.println(listAVG.size());
        int size_list_avg = listAVG.size();
        for (int i = 0; i < size_list_avg; i++) {
            int pd_id = list_product.get(i);
            double avg_pd = listAVG.get(i).getAvgRated();
            listRatingNormalized.parallelStream().filter(value -> value.getProductId() == pd_id).forEach(value -> {
                value.setValue(value.getValue() - avg_pd);
            });
        }

        List<CosineSimilarity> cosSimilarities = cosineSimilarityService.getAll();
        Map<Integer, Double> mapPredictionProduct = new HashMap<>();
//        // Rating Prediction
        List<Integer> list_pd_user_rated = new ArrayList<>();
        List<Integer> list_pd_user_unrated = new ArrayList<>();
        divide_rated_unrated(listRatingNormalized, list_product, user_id, list_pd_user_rated, list_pd_user_unrated);
        StringBuilder listProductRS = new StringBuilder();
        StringBuilder listProductRS_Show = new StringBuilder();

        list_pd_user_unrated.parallelStream().forEach(value -> {
            List<CosineSimilarity> list_cos_of_user = new ArrayList<>();
            List<RatingRSDTO> list_normalize_of_user = new ArrayList<>();
            list_cos_of_user = top_k_cosine_similarity_of_user(2, cosSimilarities, value, list_pd_user_rated);

            list_normalize_of_user = top_k_normalized_corresponding_top_k_cosine_similarity(
                    user_id, list_cos_of_user, listRatingNormalized, value);

            double a = list_cos_of_user.get(0).getRow() == list_normalize_of_user.get(0).getProductId() || list_cos_of_user.get(0).getColumn() == list_normalize_of_user.get(0).getProductId() ?
                    list_cos_of_user.get(0).getCosSimilarity() * list_normalize_of_user.get(0).getValue() +
                            list_cos_of_user.get(1).getCosSimilarity() * list_normalize_of_user.get(1).getValue() :
                    list_cos_of_user.get(0).getCosSimilarity() * list_normalize_of_user.get(1).getValue() +
                            list_cos_of_user.get(1).getCosSimilarity() * list_normalize_of_user.get(0).getValue();
            double b = Math.abs(list_cos_of_user.get(0).getCosSimilarity()) + Math.abs(list_cos_of_user.get(1).getCosSimilarity());

            if (a / b > 0) {
                mapPredictionProduct.put(value, a / b);
            }
        });

        mapPredictionProduct.entrySet().stream().sorted(Collections.reverseOrder(Map.Entry.comparingByValue())).limit(10).forEach(value -> {
            listProductRS.append(value.getKey()).append("-");
            listProductRS_Show.append(value.getKey()).append(" - ").append(value.getValue()).append(";");
        });
        if (listProductRS.length()<1)
        {
            return ResponseEntity.ok().body(new RecommendRating(user_id, listProductRS_Show.toString()));
        }
        if (!recommendRatingService.checkExistUser(user_id))
        {
            recommendRatingService.save(new RecommendRating(user_id, listProductRS.deleteCharAt(listProductRS.length() - 1).toString()));

        }
        else {
            RecommendRating recommendRating = recommendRatingService.getById(user_id);
            recommendRating.setProducts(listProductRS.deleteCharAt(listProductRS.length() - 1).toString());
            recommendRatingService.save(recommendRating);
        }

        long endTime = new Date().getTime();
        long difference = endTime - startTime;
        System.out.println("Elapsed time in milliseconds: " + difference);
        System.out.println("done");

        return ResponseEntity.ok().body(new RecommendRating(user_id, listProductRS_Show.toString()));
    }

    private List<RatingRSDTO> top_k_normalized_corresponding_top_k_cosine_similarity(int user_id, List<CosineSimilarity> list_cos_of_user, List<RatingRSDTO> listRatingNormalized, int pd_unrated_id) {
        return listRatingNormalized.stream().filter(nm -> nm.getUserId() == user_id && ((list_cos_of_user.stream().filter(value -> value.getRow() == pd_unrated_id && value.getColumn() == nm.getProductId()).findAny().orElse(null) != null) ||
                list_cos_of_user.stream().filter(value -> value.getRow() == nm.getProductId() && value.getColumn() == pd_unrated_id).findAny().orElse(null) != null)).limit(2).collect(Collectors.toList());

    }

    private List<CosineSimilarity> top_k_cosine_similarity_of_user(int k, List<CosineSimilarity> list, int pd_unrated_id, List<Integer> list_pd_user_rated) {
        return list.stream().filter(cos -> (cos.getRow() == pd_unrated_id && list_pd_user_rated.contains(cos.getColumn())) ||
                (list_pd_user_rated.contains(cos.getRow()) && cos.getColumn() == pd_unrated_id)).limit(k).collect(Collectors.toList());
    }


    private void divide_rated_unrated(List<RatingRSDTO> list_normalized, List<Integer> list_product, int user_id, List<Integer> list_rated, List<Integer> list_unrated) {
        int size_list_product = list_product.size();
        list_rated.clear();
        list_unrated.clear();
        for (int i = 0; i < size_list_product; i++) {
            int pd = list_product.get(i);
            if (list_normalized.stream().filter(value -> value.getUserId() == user_id && value.getProductId() == pd).findAny().orElse(null) != null)
                list_rated.add(pd);
            else list_unrated.add(pd);
        }
    }
}
