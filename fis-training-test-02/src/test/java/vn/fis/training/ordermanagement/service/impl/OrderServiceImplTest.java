package vn.fis.training.ordermanagement.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.fis.training.ordermanagement.domain.*;
import vn.fis.training.ordermanagement.repository.OrderRepository;
import vn.fis.training.ordermanagement.service.CustomerService;
import vn.fis.training.ordermanagement.service.OrderService;
import vn.fis.training.ordermanagement.service.ProductService;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;

    @Test
    void createOrder() {

    }

    @Test
    void addOrderItem() {
        Customer customer = new Customer(1L, "Thao", "0125416879", "nam dinh");
        Order order = new Order(1L, LocalDateTime.now(),
                customer, 100000.0, OrderStatus.PAID);
        Product product = new Product(5L, "quay", 40000D);
        OrderItem orderItem = new OrderItem(7L, order, product, 10, 50.0);

        orderService.addOrderItem(1L, orderItem);

    }

    @Test
    void removeOrderItem() {
    }

    @Test
    void updateOrderStatus() {
    }

    @Test
    void findOrdersBetween() {
    }

    @Test
    void findWaitingApprovalOrders() {
    }

    @Test
    void findOrdersByOrderStatus() {
    }

    @Test
    void findOrdersByCustomer() {
    }
}