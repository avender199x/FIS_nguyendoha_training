package com.nguyendoha.Test_final_fis.dto;

import com.nguyendoha.Test_final_fis.model.Order;
import com.nguyendoha.Test_final_fis.model.Product;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class OrderItemDto {
    private Long id;
    private Long order;
    private Long product;
    private Integer quantity;
    private Double amount;
}
