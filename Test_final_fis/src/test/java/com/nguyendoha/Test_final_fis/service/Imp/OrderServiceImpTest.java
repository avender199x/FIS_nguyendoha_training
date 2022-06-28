package com.nguyendoha.Test_final_fis.service.Imp;

import com.nguyendoha.Test_final_fis.dto.OrderDto;
import com.nguyendoha.Test_final_fis.dto.OrderItemDto;
import com.nguyendoha.Test_final_fis.model.Order;
import com.nguyendoha.Test_final_fis.model.OrderItem;
import com.nguyendoha.Test_final_fis.model.OrderStatus;
import com.nguyendoha.Test_final_fis.service.CustomerService;
import com.nguyendoha.Test_final_fis.service.OrderService;
import com.nguyendoha.Test_final_fis.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImpTest {
    @Autowired
    private OrderService service;

    @Test
    void create() {
        OrderDto orderDto = OrderDto.builder()
                .customer(1l)
                .totalAmount(123123d)
                .orderDateTime(LocalDateTime.now())
                .status(OrderStatus.CREATED)
                .build();
        List<OrderItemDto> orderItemDtos = new ArrayList<>();
        OrderItemDto itemDto = OrderItemDto.builder()
                .product(1l)
                .quantity(12)
                .build();
        orderItemDtos.add(itemDto);
        orderDto.setOrderItems(orderItemDtos);
        service.Create(orderDto);
    }

    @Test
    void findAll() {
        service.findAll(Pageable.unpaged());
    }

    @Test
    void findById() {
        service.findById(1l);
    }

    @Test
    void delete() {
        service.delete(1l);
    }

    @Test
    void addOrderItem() {
        OrderItemDto orderItemDto = OrderItemDto.builder()
                .order(3l)
                .product(1l)
                .quantity(1)
                .build();
        service.addOrderItem(orderItemDto);
    }

    @Test
    void removeOrderItem() {
        OrderItemDto orderItemDto = OrderItemDto.builder()
                .id(7l)
                .build();
        service.removeOrderItem(orderItemDto);
    }

    @Test
    void updateStatusPaid() {
        service.updateStatusPaid(1l);
    }

    @Test
    void updateStatusCancel() {
        service.updateStatusCancel(2l);
    }
}