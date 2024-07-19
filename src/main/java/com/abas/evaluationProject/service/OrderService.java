package com.abas.evaluationProject.service;

import com.abas.evaluationProject.model.DetailModel;

import java.util.List;
import java.util.Map;

public interface OrderService {
    String getTotalAmount(Map<String, List<DetailModel>> data);

    String getAverageAmount(Map<String, List<DetailModel>> data);

    String getAverageItemAmount(Map<String, List<DetailModel>> data);
    String getItemCount(Map<String, List<DetailModel>> data);
}
