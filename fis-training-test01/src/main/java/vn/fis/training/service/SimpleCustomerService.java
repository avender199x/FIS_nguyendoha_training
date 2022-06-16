package vn.fis.training.service;

import vn.fis.training.domain.Customer;
import vn.fis.training.store.InMemoryCustomerStore;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleCustomerService implements CustomerService {

    private InMemoryCustomerStore customerStore;

    @Override
    public Customer findById(String id) {
        Customer findById = customerStore.findAll().stream().filter(customer -> customer.getId().equals(id)).findFirst().get();
        return findById;
    }

    @Override
    public Customer createCustomer(Customer customer) {
        if (customerStore.findAll().stream().filter(customer1 -> customer1.getName().equals(customer.getName())) != null) {
            throw new RuntimeException("customer ton tai");
        } else {
            return customerStore.insertOrUpdate(customer);
        }
    }

    @Override
    public Customer updateCustomer(Customer customer) {
        if (customerStore.findAll().stream().filter(customer1 -> customer1.getId().equals(customer.getId())) != null) {
            return customerStore.insertOrUpdate(customer);
        } else {
            throw new RuntimeException("customer ko ton tai");
        }
    }

    @Override
    public void deleteCustomerById(String id) {
        customerStore.deleteById(id);
    }

    @Override
    public List<Customer> findAllOrderByNameAsc() {
        return customerStore.findAll().stream().sorted(Comparator.comparing(Customer::getName)).collect(Collectors.toList());
    }

    @Override
    public List<Customer> findAllOrderByNameLimit(int limit) {
        //TODO: Implement method tho dung dac ta cua CustomerService interface
        return customerStore.findAll().stream().sorted(Comparator.comparing(Customer::getName)).limit(limit).collect(Collectors.toList());
    }

    @Override
    public List<Customer> findAllCustomerByNameLikeOrderByNameAsc(String custName, int limit) {
        List<Customer> findByName = customerStore.findAll().stream().filter(customer -> customer.getName().equals(custName)).collect(Collectors.toList());
        List<Customer> elseName = customerStore.findAll().stream().filter(customer -> customer.getName() != custName).sorted(Comparator.comparing(Customer::getName)).collect(Collectors.toList());
        for (Customer customer : elseName) {
            findByName.add(customer);
        }
        return findByName.stream().limit(limit).collect(Collectors.toList());
    }

    @Override
    public List<SummaryCustomerByAgeDTO> summaryCustomerByAgeOrderByAgeDesc() {
        return null;
    }

}
