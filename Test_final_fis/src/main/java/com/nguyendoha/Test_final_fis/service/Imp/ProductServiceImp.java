package com.nguyendoha.Test_final_fis.service.Imp;

import com.nguyendoha.Test_final_fis.model.Product;
import com.nguyendoha.Test_final_fis.repository.ProductRepository;
import com.nguyendoha.Test_final_fis.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

@Slf4j
@Service
public class ProductServiceImp implements ProductService {
    @Autowired
    private ProductRepository productRepository;

    @Transactional
    @Override
    public Product save(Product product) {
        return productRepository.save(product);
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Product> findAll(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Product> findById(Long id) {
        return Optional.ofNullable(productRepository.findById(id).orElseThrow(
                () -> {
                    throw new IllegalArgumentException(String.format("Not found Customer with id %s", id));
                }));
    }

    @Transactional
    @Override
    public Product update(Long id, Product product) {
        Optional<Product> update = productRepository.findById(id);
        if (update.isPresent()) {
            update.get().setPrice(product.getPrice());
            update.get().setAvailable(product.getAvailable());
            update.get().setName(product.getName());
            return productRepository.save(update.get());
        } else {
            log.error("\n update false" + "\n Time : " + LocalDate.now() + "\n product : " + product);
            throw new RuntimeException("product does not exist");
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        productRepository.deleteById(id);
    }
}
