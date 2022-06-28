package com.nguyendoha.Test_final_fis.service.Imp;

import com.nguyendoha.Test_final_fis.model.Customer;
import com.nguyendoha.Test_final_fis.service.CustomerService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Pageable;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceImpTest {
    @Autowired
    private CustomerService service;

    @Test
    void save() {
        Customer customer = new Customer();
        customer.setAddress("Ha Noi Viet Nammmmm");
        customer.setMobile("0123456789");
        customer.setName("nguyen do ha test");
        service.save(customer);
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
        Customer customer = new Customer();
        customer.setMobile("9876543210");
        customer.setAddress("hanoi sdasdasdsadasd");
        customer.setName("nguyen do ha update1");
        service.update(1l, customer);
    }

    @Test
    void delete() {
        service.delete(1l);
    }
}