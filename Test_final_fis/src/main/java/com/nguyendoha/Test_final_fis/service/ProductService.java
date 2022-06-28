package com.nguyendoha.Test_final_fis.service;


import com.nguyendoha.Test_final_fis.model.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface ProductService {
    public Product save(Product product);

    public Page<Product> findAll(Pageable pageable);

    public Optional<Product> findById(Long id);

    public Product update(Long id, Product product);

    public void delete(Long id);
}
