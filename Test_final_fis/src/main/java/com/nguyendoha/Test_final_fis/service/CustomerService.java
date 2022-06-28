package com.nguyendoha.Test_final_fis.service;


import com.nguyendoha.Test_final_fis.model.Customer;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface CustomerService {
    public List<Customer> save(Customer customer);

    public Page<Customer> findAll(Pageable pageable);

    public Optional<Customer> findById(Long id);

    public Customer update(Long id, Customer customer);

    public void delete(Long id);
}
