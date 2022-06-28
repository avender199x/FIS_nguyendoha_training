package com.nguyendoha.Test_final_fis.service.Imp;

import com.nguyendoha.Test_final_fis.controller.ExceptionHandler.CustomerExceptionError;
import com.nguyendoha.Test_final_fis.controller.ExceptionHandler.CustomerNotFoundException;
import com.nguyendoha.Test_final_fis.model.Customer;
import com.nguyendoha.Test_final_fis.repository.CustomerRepository;
import com.nguyendoha.Test_final_fis.service.CustomerService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.regex.Pattern;

@Slf4j
@Service
public class CustomerServiceImp implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;

    @Transactional
    @Override
    public List<Customer> save(Customer customer) {
        if (customer.getName().length() > 10
                && customer.getMobile().length() == 10
                && customer.getAddress().length() > 10
                && Pattern.compile("^\\d{10}$").matcher(customer.getMobile()).matches()) {
            return customerRepository.findAll();
        } else {
            log.error("\n Save false : " + "\n Time : " + LocalDate.now() + "\ncustomer : " + customer);
            throw new CustomerExceptionError("error create customer , please re-enter");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Customer> findAll(Pageable pageable) {
        return customerRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Customer> findById(Long id) {
        return Optional.ofNullable(customerRepository.findById(id).orElseThrow(
                () -> {
                    throw new CustomerNotFoundException(String.format("Not found Customer with id %s", id));
                }));
    }

    @Transactional
    @Override
    public Customer update(Long id, Customer customer) {
        Optional<Customer> update = customerRepository.findById(id);
        if (update.isPresent() && customer.getName().length() > 10
                && customer.getMobile().length() == 10
                && customer.getAddress().length() > 10
                && Pattern.compile("^\\d{10}$").matcher(customer.getMobile()).matches()) {

            update.get().setMobile(customer.getMobile());
            update.get().setName(customer.getName());
            update.get().setAddress(customer.getAddress());
            return customerRepository.save(update.get());
        } else {
            log.error("\n update false" + "\n Time : " + LocalDate.now() + "\n Customer : " + customer);
            throw new CustomerNotFoundException("customer does not exist or you entered wrong");
        }
    }

    @Transactional
    @Override
    public void delete(Long id) {
        customerRepository.deleteById(id);
    }
}
