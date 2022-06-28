package com.nguyendoha.Test_final_fis.dto;

import com.nguyendoha.Test_final_fis.model.Customer;
import com.nguyendoha.Test_final_fis.model.OrderItem;
import com.nguyendoha.Test_final_fis.model.OrderStatus;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderDto {
    private Long id;
    private LocalDateTime orderDateTime;
    private Long customer;
    private List<OrderItemDto> orderItems;
    private Double totalAmount;
    @Enumerated(EnumType.STRING)
    private OrderStatus status;
}
