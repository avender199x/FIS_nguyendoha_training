package vn.fis.training.ordermanagement.service;

import vn.fis.training.ordermanagement.domain.Product;

import java.util.List;
import java.util.Optional;

public interface ProductService {
    public Product SaveProduct(Product product);

    public List<Product> findAll();

    public Optional<Product> findById(Long id);

    public Product updateProduct(Long id, Product product);

    public void deleteProduct(Long id);
}
