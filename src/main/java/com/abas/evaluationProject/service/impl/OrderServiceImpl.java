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
    public String getTotalAmount(Map<String, List<DetailModel>> data) {
        StringBuilder responseBuilder = new StringBuilder();

        data.forEach((order, details) -> {

                    final float[] toplamTutar = {0};
                    details.forEach(detail -> {
                        float tutar = detail.getAmount() * detail.getPrice();
                        toplamTutar[0] += tutar;


                    });
                    responseBuilder.append("Sipariş: ").append(order)
                            .append(", Toplam Tutar: ").append(toplamTutar[0]).append("\n");
                    responseBuilder.append("\n");
                }
        );

        return responseBuilder.toString();
    }

    @Override
    public String getAverageAmount(Map<String, List<DetailModel>> data) {
        StringBuilder responseBuilder = new StringBuilder();


        data.forEach((order, details) -> {

                    final float[] toplamTutar = {0};
                    final float[] toplamMiktar = {0};
                    details.forEach(detail -> {
                        toplamMiktar[0] += detail.getAmount();
                        float tutar = detail.getAmount() * detail.getPrice();
                        toplamTutar[0] += tutar;


                    });
                    float ortalamaTutar = toplamTutar[0] / toplamMiktar[0];
                    responseBuilder.append("Sipariş: ").append(order)
                            .append(", Ortalama Tutar: ").append(ortalamaTutar).append("\n");
                    responseBuilder.append("\n");
                }
        );

        return responseBuilder.toString();
    }

    @Override
    public String getAverageItemAmount(Map<String, List<DetailModel>> data) {
        StringBuilder responseBuilder = new StringBuilder();


        data.forEach((order, details) -> {
                    Map<String, Float> numaraTutarMap = new HashMap<>();
                    Map<String, Integer> numaraMiktarMap = new HashMap<>();

                    details.forEach(detail -> {
                        String key = detail.getNumber();
                        if (numaraTutarMap.containsKey(key)) {
                            numaraTutarMap.compute(key, (k, tutar) -> tutar + detail.getAmount() * detail.getPrice());
                            numaraMiktarMap.compute(key, (k, miktar) -> miktar + detail.getAmount());

                        } else {
                            numaraTutarMap.put(key, detail.getPrice() * detail.getAmount());
                            numaraMiktarMap.put(key, detail.getAmount());
                        }


                    });

                    numaraTutarMap.forEach((numara, tutar) -> {
                        Integer miktar = numaraMiktarMap.get(numara);
                        float ortalamaTutar = tutar / miktar;
                        responseBuilder.append("Sipariş: ").append(order).append(", Mal Numarası: ").append(numara)
                                .append(", Ortalama Tutar: ").append(ortalamaTutar).append("\n");
                    });

                    responseBuilder.append("\n");
                }
        );

        return responseBuilder.toString();
    }

    @Override
    public String getItemCount(Map<String, List<DetailModel>> data) {
        StringBuilder responseBuilder = new StringBuilder();

        data.forEach((order, details) -> {
            Map<String, Integer> siparisMiktarMap = new HashMap<>();


            details.forEach(detail -> {
                String key = detail.getNumber();
                if (siparisMiktarMap.containsKey(key)) {
                    siparisMiktarMap.compute(key, (k, miktar) -> miktar + detail.getAmount());
                } else {
                    siparisMiktarMap.put(key, detail.getAmount());
                }
            });

            siparisMiktarMap.forEach((numara, miktar) -> {
                responseBuilder.append("Mal Numarası: ").append(numara).append(", Sipariş: ").append(order)
                        .append(", Miktar: ").append(miktar).append("\n");

            });
            responseBuilder.append("\n");

        });

        return responseBuilder.toString();

    }
}
