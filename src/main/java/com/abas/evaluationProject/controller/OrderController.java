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
        return orderService.getTotalAmount(data);
    }


    @PostMapping("/average")
    public String getAverageAmount(@RequestBody Map<String, List<DetailModel>> data) {
        return orderService.getAverageAmount(data);
    }

    @PostMapping("/average/item")
    public String getAverageItemAmount(@RequestBody Map<String, List<DetailModel>> data) {
        return orderService.getAverageItemAmount(data);
    }

    @GetMapping("/count")
    public String getItemCount(@RequestBody Map<String, List<DetailModel>> data) {
        return orderService.getItemCount(data);
    }
}
