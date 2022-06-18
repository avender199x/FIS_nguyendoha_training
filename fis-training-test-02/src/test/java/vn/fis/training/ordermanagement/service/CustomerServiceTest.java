package vn.fis.training.ordermanagement.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.fis.training.ordermanagement.domain.Customer;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CustomerServiceTest {
    @Autowired
    private CustomerService service;

    @Test
    void createCustomer() {
        Customer customer = new Customer(1l, "Nguyen Do ha", "036990606", "Ha Noi");
        service.createCustomer(customer);
    }

    @Test
    void updateCustomer() {
    }

    @Test
    void deleteCustomerById() {
    }

    @Test
    void findAll() {
        List<Customer> customers = service.findAll();
        System.out.println(customers);
    }

    @Test
    void findByMobileNumber() {
    }
}