package vn.fis.training.ordermanagement.service.impl;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import vn.fis.training.ordermanagement.domain.*;
import vn.fis.training.ordermanagement.service.OrderService;
import vn.fis.training.ordermanagement.service.ProductService;

@SpringBootTest
class OrderServiceImplTest {
    @Autowired
    private OrderService orderService;
    @Autowired
    private ProductService productService;

    @Test
    void createOrder() {
        Order order = new Order();
        order.setTotalAmount(123123d);
        order.setStatus(OrderStatus.APPROVED);
        orderService.createOrder(order);
    }

    @Test
    void addOrderItem() {
        OrderItem item = new OrderItem();
        item.setAmount(123123d);
        item.setProduct(productService.findById(1l).get());
        item.setQuantity(1231);
        orderService.addOrderItem(1l, item);
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