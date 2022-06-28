package com.nguyendoha.Test_final_fis.repository;

import com.nguyendoha.Test_final_fis.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
}
