package com.nguyendoha.Test_final_fis.controller;

import com.nguyendoha.Test_final_fis.model.Customer;
import com.nguyendoha.Test_final_fis.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/customer")
public class CustomerController {
    @Autowired
    private CustomerService service;

    @GetMapping("/{PageNumber}/{PageSize}")
    public Page<Customer> findAll(@PathVariable(name = "PageNumber") Integer n, @PathVariable(name = "PageSize") Integer s) {
        return service.findAll(PageRequest.of(n, s));
    }

    @GetMapping("/{id}")
    public Optional<Customer> findById(@PathVariable(name = "id") Long id) {
        return service.findById(id);
    }

    @PutMapping("/")
    public List<Customer> addCustomer(@RequestBody Customer customer) {
        return service.save(customer);
    }

    @PostMapping("/{id}")
    public Customer updateCustomer(@PathVariable(name = "id") Long id, @RequestBody Customer customer) {
        return service.update(id, customer);
    }

    @DeleteMapping("/{id}")
    public void deleteCustomer(@PathVariable(name = "id") Long id) {
        service.delete(id);
    }
}
