package com.abas.evaluationProject.service;

import com.abas.evaluationProject.model.DetailModel;

import java.util.List;
import java.util.Map;

public interface OrderService {
    Map<String, Float> getTotal(Map<String, List<DetailModel>> data);

    Map<String, Float> getAverageAmount(Map<String, List<DetailModel>> data);

    Map<String, Float> getAverageItemAmount(Map<String, List<DetailModel>> data);

    String getItemCount(Map<String, List<DetailModel>> data);
}
