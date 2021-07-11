package com.TP.IService;

import com.TP.entity.CosineSimilarity;

import java.util.List;

public interface ICosineSimilarityService {
    public void saveAll(List<CosineSimilarity> list);

    public CosineSimilarity save(CosineSimilarity cosineSimilarityDTO);

    public CosineSimilarity getByColumnAndRow(int row, int column);

    public List<CosineSimilarity> getAll();

    public void removeAll();
}
