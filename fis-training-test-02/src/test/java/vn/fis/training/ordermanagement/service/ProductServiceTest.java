package vn.fis.training.ordermanagement.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.fis.training.ordermanagement.domain.Product;
import vn.fis.training.ordermanagement.repository.ProductRepository;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ProductServiceTest {
    @Autowired
    private ProductService service;


    @Test
    void saveProduct() {
        Product product = new Product(1l, "quan ao", 10000000d);
        Product product1 = new Product(2l, "iphone", 15000000d);
        service.SaveProduct(product);
        service.SaveProduct(product1);
    }

    @Test
    void findAll() {
        List<Product> products = service.findAll();
        System.out.println(products);
    }

    @Test
    void findById() {
        System.out.println(service.findById(2l));
    }

    @Test
    void updateProduct() {

    }

    @Test
    void deleteProduct() {
    }
}