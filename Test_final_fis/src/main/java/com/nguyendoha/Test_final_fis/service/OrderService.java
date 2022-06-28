package com.nguyendoha.Test_final_fis.service;

import com.nguyendoha.Test_final_fis.dto.OrderDto;
import com.nguyendoha.Test_final_fis.dto.OrderItemDto;
import com.nguyendoha.Test_final_fis.model.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface OrderService {
    public Order Create(OrderDto orderDto);

    public Page<Order> findAll(Pageable pageable);

    public Optional<Order> findById(Long id);

    public void delete(Long id);

    public Order addOrderItem(OrderItemDto orderItemDto);

    public Order removeOrderItem(OrderItemDto orderItemDto);

    public Order updateStatusPaid(Long id);

    public Order updateStatusCancel(Long id);
}
