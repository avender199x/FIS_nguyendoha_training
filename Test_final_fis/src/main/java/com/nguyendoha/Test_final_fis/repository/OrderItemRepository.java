package com.nguyendoha.Test_final_fis.repository;

import com.nguyendoha.Test_final_fis.model.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
