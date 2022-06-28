package com.nguyendoha.Test_final_fis.service.Imp;

import com.nguyendoha.Test_final_fis.model.Product;
import com.nguyendoha.Test_final_fis.service.ProductService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceImpTest {
    @Autowired
    private ProductService service;

    @Test
    void save() {
        Product product = new Product();
        product.setName("iphone 11");
        product.setAvailable(12l);
        product.setPrice(123123123d);
        service.save(product);
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
    void update() {
        Product product = new Product();
        product.setName("iphone 11");
        product.setAvailable(12l);
        product.setPrice(123123123d);
        service.update(1l, product);
    }

    @Test
    void delete() {
        service.delete(1l);
    }
}