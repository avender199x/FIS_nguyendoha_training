package vn.fis.training.ordermanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fis.training.ordermanagement.domain.Customer;
import vn.fis.training.ordermanagement.repository.CustomerRepository;
import vn.fis.training.ordermanagement.service.CustomerService;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    private CustomerRepository customerRepository;


    @Override
    public Customer createCustomer(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        if (customerRepository.findById(customer.getId()).isPresent()) {
            Customer update = customerRepository.getReferenceById(customer.getId());
            update.setName(customer.getName());
            update.setMobile(customer.getMobile());
            update.setAddress(customer.getAddress());
            return customerRepository.save(update);
        } else {
            throw new RuntimeException("customer khong ton tai");
        }
    }

    @Override
    public void deleteCustomerById(Long customerId) {
        customerRepository.deleteById(customerId);
    }

    @Override
    public List<Customer> findAll() {
        return customerRepository.findAll();
    }

    @Override
    public Customer findByMobileNumber(String mobileNumber) {
        return customerRepository.findByMobile(mobileNumber);
    }
}
