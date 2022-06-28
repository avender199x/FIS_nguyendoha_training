package com.nguyendoha.Test_final_fis.controller;

import com.nguyendoha.Test_final_fis.model.Product;
import com.nguyendoha.Test_final_fis.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductService service;

    @GetMapping("/{PageNumber}/{PageSize}")
    public Page<Product> findAll(@PathVariable(name = "PageNumber") Integer n, @PathVariable(name = "PageSize") Integer s) {
        return service.findAll(PageRequest.of(n, s));
    }

    @GetMapping("/{id}")
    public Optional<Product> findById(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @PostMapping("/")
    public Product addProduct(@RequestBody Product product) {
        return service.save(product);
    }

    @PutMapping("/{id}")
    public Product updateProduct(@PathVariable(name = "id") Long id, @RequestBody Product product) {
        return service.update(id, product);
    }

    @DeleteMapping("/{id}")
    public void deleteProduct(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }
}
