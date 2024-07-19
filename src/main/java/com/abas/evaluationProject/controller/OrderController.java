package com.abas.evaluationProject.controller;

import com.abas.evaluationProject.model.DetailModel;
import com.abas.evaluationProject.service.OrderService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }


    @GetMapping("/total")
    public String getTotalAmount(@RequestBody Map<String, List<DetailModel>> data) {
        StringBuilder responseBuilder = new StringBuilder();

        Map<String, Float> result = orderService.getTotal(data);
        result.forEach((order, total) -> {
            responseBuilder.append("Sipariş: ").append(order)
                    .append(", Toplam Tutar: ").append(total).append("\n");
        });

        return responseBuilder.toString();
    }


    @PostMapping("/average")
    public String getAverageAmount(@RequestBody Map<String, List<DetailModel>> data) {
        StringBuilder responseBuilder = new StringBuilder();

        Map<String, Float> result = orderService.getAverageAmount(data);
        result.forEach((order, average) -> {
            responseBuilder.append("Sipariş: ").append(order)
                    .append(", Ortalama Tutar: ").append(average).append("\n");
        });
        return responseBuilder.toString();
    }


    @PostMapping("/average/item")
    public String getAverageItemAmount(@RequestBody Map<String, List<DetailModel>> data) {
        StringBuilder responseBuilder = new StringBuilder();

        Map<String, Float> result = orderService.getAverageItemAmount(data);
        result.forEach((number, average) -> {
            responseBuilder.append("Mal Numarası: ").append(number)
                    .append(", Ortalama Tutar: ").append(average).append("\n");
        });

        return responseBuilder.toString();
    }

    @GetMapping("/count")
    public String getItemCount(@RequestBody Map<String, List<DetailModel>> data) {
        return orderService.getItemCount(data);
    }
}
