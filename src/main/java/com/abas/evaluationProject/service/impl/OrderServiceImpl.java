package com.abas.evaluationProject.service.impl;

import com.abas.evaluationProject.model.DetailModel;
import com.abas.evaluationProject.service.OrderService;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {

    @Override
    public Map<String, Float> getTotal(Map<String, List<DetailModel>> data) {
        Map<String, Float> totalMap = new HashMap<>();
        data.forEach((order, details) -> {

                    final float[] toplamTutar = {0};
                    details.forEach(detail -> {
                        float tutar = detail.getAmount() * detail.getPrice();
                        toplamTutar[0] += tutar;

                    });
                    totalMap.put(order, toplamTutar[0]);

                }
        );

        return totalMap;
    }

    private Map<String, Float> getTotalAmount(Map<String, List<DetailModel>> data) {
        Map<String, Float> totalAmountMap = new HashMap<>();
        data.forEach((order, details) -> {
            final float[] totalAmount = {0};
            details.forEach(detail -> {
                totalAmount[0] += detail.getAmount();
            });
            totalAmountMap.put(order, totalAmount[0]);
        });
        return totalAmountMap;
    }

    private Map<String, Integer> getTotalAmountForNumber(Map<String, List<DetailModel>> data) {
        Map<String, Integer> totalAmountForNumberMap = new HashMap<>();

        data.forEach((order, details) -> {

            details.forEach(detail -> {
                if (totalAmountForNumberMap.containsKey(detail.getNumber())) {
                    totalAmountForNumberMap.put(detail.getNumber(),
                            totalAmountForNumberMap.get(detail.getNumber()) + detail.getAmount());
                } else {
                    totalAmountForNumberMap.put(detail.getNumber(), detail.getAmount());
                }

            });

        });

        return totalAmountForNumberMap;
    }

    private Map<String, Float> getTotalForNumber(Map<String, List<DetailModel>> data) {
        Map<String, Float> totalForNumberMap = new HashMap<>();

        data.forEach((order, details) -> {

            details.forEach(detail -> {
                if (totalForNumberMap.containsKey(detail.getNumber())) {
                    totalForNumberMap.put(detail.getNumber(),
                            totalForNumberMap.get(detail.getNumber()) + detail.getAmount() * detail.getPrice());
                } else {
                    totalForNumberMap.put(detail.getNumber(), detail.getAmount() * detail.getPrice());
                }

            });

        });

        return totalForNumberMap;
    }


    @Override
    public Map<String, Float> getAverageAmount(Map<String, List<DetailModel>> data) {
        Map<String, Float> averageMap = new HashMap<>();


        Map<String, Float> totalMap = getTotal(data);
        Map<String, Float> totalAmountMap = getTotalAmount(data);
        totalMap.forEach((order, total) -> {
            averageMap.put(order, total / totalAmountMap.get(order));
        });

        return averageMap;
    }

    @Override
    public Map<String, Float> getAverageItemAmount(Map<String, List<DetailModel>> data) {
        Map<String, Float> averageItemMap = new HashMap<>();

        Map<String, Integer> totalAmountForNumberMap = getTotalAmountForNumber(data);
        Map<String, Float> totalForNumber = getTotalForNumber(data);

        totalForNumber.forEach((number, total) -> {
            averageItemMap.put(number, total / totalAmountForNumberMap.get(number));
        });

        return averageItemMap;
    }

    @Override
    public String getItemCount(Map<String, List<DetailModel>> data) {
        StringBuilder responseBuilder = new StringBuilder();

        data.forEach((order, details) -> {
            Map<String, Integer> totalAmountMap = new HashMap<>();


            details.forEach(detail -> {
                String key = detail.getNumber();
                if (totalAmountMap.containsKey(key)) {
                    totalAmountMap.compute(key, (k, amount) -> amount + detail.getAmount());
                } else {
                    totalAmountMap.put(key, detail.getAmount());
                }
            });

            totalAmountMap.forEach((numara, miktar) -> {
                responseBuilder.append("Mal Numarası: ").append(numara).append(", Sipariş: ").append(order)
                        .append(", Miktar: ").append(miktar).append("\n");

            });
            responseBuilder.append("\n");

        });

        return responseBuilder.toString();

    }
}
