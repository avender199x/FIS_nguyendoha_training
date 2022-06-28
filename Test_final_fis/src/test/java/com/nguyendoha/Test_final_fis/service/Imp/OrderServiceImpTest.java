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
        OrderDto orderDto = new OrderDto();
        orderDto.setCustomer(1l);
        orderDto.setTotalAmount(122131d);
        orderDto.setOrderDateTime(LocalDateTime.now());
        orderDto.setStatus(OrderStatus.CREATED);
        List<OrderItemDto> orderItemDtos = new ArrayList<>();
        OrderItemDto itemDto = new OrderItemDto();
        itemDto.setAmount(12d);
        itemDto.setProduct(1l);
        itemDto.setQuantity(12);
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
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setOrder(3l);
        orderItemDto.setAmount(12d);
        orderItemDto.setProduct(1l);
        orderItemDto.setQuantity(12);
        service.addOrderItem(orderItemDto);
    }

    @Test
    void removeOrderItem() {
        OrderItemDto orderItemDto = new OrderItemDto();
        orderItemDto.setId(1l);
        orderItemDto.setOrder(3l);
        orderItemDto.setAmount(12d);
        orderItemDto.setProduct(1l);
        orderItemDto.setQuantity(12);
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