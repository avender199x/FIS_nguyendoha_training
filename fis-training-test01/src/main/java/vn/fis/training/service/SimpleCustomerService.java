package vn.fis.training.service;

import vn.fis.training.domain.Customer;
import vn.fis.training.exception.CustomerNotFoundException;
import vn.fis.training.store.InMemoryCustomerStore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.UUID;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class SimpleCustomerService implements CustomerService {

    private InMemoryCustomerStore customerStore;

    @Override
    public Customer findById(String id) throws Exception {
        List<Customer> customers = customerStore.findAll();
        Customer customer = null;
        for (Customer customer1 : customers) {
            if (customer1.getId().equals(id.trim())) {
                customer = customer1;
            }
        }
        if (customer != null) {
            return customer;
        } else {
            throw new Exception("Id khong ton tai");
        }
    }

    @Override
    public Customer createCustomer(Customer customer) throws Exception {
        List<Customer> customers = customerStore.findAll();
        UUID uuid = UUID.randomUUID();
        Customer customer1 = new Customer();
        String phone = "^[0]( *[\\d] *){9}$";
        String name = "^[a-zA-Z ]{5,50}$";
        customer1.setId(uuid.toString());
        customer1.setStatus(customer.getStatus());
        customer1.setCreateDateTime(customer.getCreateDateTime());
        if (customer.getBirthDay().getYear() < 2013) {
            customer1.setBirthDay(customer.getBirthDay());
        } else {
            throw new Exception("tuoi ko kha dung");
        }
        if (Pattern.matches(name, customer.getName())) {
            customer1.setName(customer.getName());
        } else {
            throw new Exception("ten khong kha dung");
        }
        if (Pattern.matches(phone, customer.getMobile())) {
            for (Customer customer2 : customers) {
                if (customer.getMobile().equals(customer2.getMobile())) {
                    throw new Exception("sdt da ton tai");
                } else {
                    customer1.setMobile(customer.getMobile());
                    break;
                }
            }
        } else {
            throw new Exception("so dien thoai khong kha dung");
        }
        return customerStore.insertOrUpdate(customer1);
    }

    @Override
    public Customer updateCustomer(Customer customer) throws Exception {
        List<Customer> customers = customerStore.findAll();
        List<Customer> checkuser = customers.stream().filter(customer1 -> customer1.getId().equals(customer.getId())).collect(Collectors.toList());
        if (checkuser != null) {
            customerStore.insertOrUpdate(customer);
        } else {
            throw new Exception("Id khong ton tai");
        }
        return customer;
    }

    @Override
    public void deleteCustomerById(String id) throws Exception {
        List<Customer> customers = customerStore.findAll();
        List<Customer> checkuser = customers.stream().filter(customer -> customer.getId().equals(id)).collect(Collectors.toList());
        if (checkuser != null) {
            customerStore.deleteById(id);
        } else {
            throw new Exception("Id khong ton tai");
        }
    }

    @Override
    public List<Customer> findAllOrderByNameAsc() {
        List<Customer> customers = customerStore.findAll();
        List<Customer> findallasc = customers.stream().sorted(Comparator.comparing(Customer::getId)).collect(Collectors.toList());
        return findallasc;
    }

    @Override
    public List<Customer> findAllOrderByNameLimit(int limit) {
        List<Customer> customers = customerStore.findAll();
        List<Customer> FindAllByNameLimit = customers.stream().sorted(Comparator.comparing(Customer::getId)).limit(limit).collect(Collectors.toList());
        return FindAllByNameLimit;
    }

    @Override
    public List<Customer> findAllCustomerByNameLikeOrderByNameAsc(String custName, int limit) {
        List<Customer> customers = customerStore.findAll();
        List<Customer> danhsachten = customers.stream().filter(customer -> customer.getName().equals(custName)).collect(Collectors.toList());
        List<Customer> danhsachkhac = customers.stream().filter(customer -> customer.getName() != custName).sorted(Comparator.comparing(Customer::getName)).collect(Collectors.toList());
        for (Customer customer : danhsachkhac) {
            danhsachten.add(customer);
        }
        danhsachten = danhsachten.stream().limit(limit).collect(Collectors.toList());
        return danhsachten;
    }

    @Override
    public List<SummaryCustomerByAgeDTO> summaryCustomerByAgeOrderByAgeDesc() {

        return null;
    }

}
