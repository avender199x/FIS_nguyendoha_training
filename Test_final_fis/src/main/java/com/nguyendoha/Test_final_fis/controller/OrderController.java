package com.nguyendoha.Test_final_fis.controller;

import com.nguyendoha.Test_final_fis.dto.OrderDto;
import com.nguyendoha.Test_final_fis.dto.OrderItemDto;
import com.nguyendoha.Test_final_fis.model.Order;
import com.nguyendoha.Test_final_fis.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    private OrderService service;

    @GetMapping("/{PageNumber}/{PageSize}")
    public Page<Order> findAll(@PathVariable(name = "PageNumber") Integer n, @PathVariable(name = "PageSize") Integer s) {
        return service.findAll(PageRequest.of(n, s));
    }

    @GetMapping("/{id}")
    public Optional<Order> findById(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @PutMapping("/")
    public Order Create(@RequestBody OrderDto orderDto) {
        return service.Create(orderDto);
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }

    @PutMapping("/addorderitem")
    public Order addOrderItem(@RequestBody OrderItemDto orderItemDto) {

        return service.addOrderItem(orderItemDto);
    }

    @PostMapping("/removeorderitem")
    public Order removeOrderItem(@RequestBody OrderItemDto orderItemDto) {
        return service.removeOrderItem(orderItemDto);
    }

    @PostMapping("/paid/{id}")
    public Order updateStatusPaid(@PathVariable("id") Long id) {
        return service.updateStatusPaid(id);
    }

    @PostMapping("/cancel/{id}")
    public Order updateStatusCancel(@PathVariable(name = "id") Long id) {
        return service.updateStatusCancel(id);
    }
}
