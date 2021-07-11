package com.TP.service;

import com.TP.DAO.CosineSimilarityDAO;
import com.TP.IService.ICosineSimilarityService;
import com.TP.entity.CosineSimilarity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CosineSimilarityService implements ICosineSimilarityService {
    @Autowired
    private CosineSimilarityDAO cosineSimilarityDAO;

    public void saveAll(List<CosineSimilarity> list){
        cosineSimilarityDAO.saveAll(list);
    }

    public CosineSimilarity save(CosineSimilarity cosineSimilarityDTO) {return cosineSimilarityDAO.save(cosineSimilarityDTO);}

    public CosineSimilarity getByColumnAndRow(int row, int column){
        return cosineSimilarityDAO.findByColumnAndRow(column, row);
    }

    public List<CosineSimilarity> getAll(){
        return cosineSimilarityDAO.findAll();
    }

    public void removeAll(){
        cosineSimilarityDAO.deleteAll();;
    }
}
