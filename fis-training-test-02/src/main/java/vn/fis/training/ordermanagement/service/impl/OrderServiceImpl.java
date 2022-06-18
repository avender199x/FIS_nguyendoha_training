package vn.fis.training.ordermanagement.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.fis.training.ordermanagement.domain.Customer;
import vn.fis.training.ordermanagement.domain.Order;
import vn.fis.training.ordermanagement.domain.OrderItem;
import vn.fis.training.ordermanagement.domain.OrderStatus;
import vn.fis.training.ordermanagement.repository.OrderItemRepository;
import vn.fis.training.ordermanagement.repository.OrderRepository;
import vn.fis.training.ordermanagement.service.OrderService;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;


@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public Order createOrder(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order addOrderItem(Long orderId, OrderItem orderItem) {
        if (orderRepository.getReferenceById(orderId).getId() != null && orderId.equals(orderItem.getId())) {
            orderItemRepository.save(orderItem);
            return orderRepository.getReferenceById(orderId);
        } else {
            throw new RuntimeException("order khong ton tai");
        }
    }

    @Override
    public Order removeOrderItem(Long orderId, OrderItem orderItem) {
        orderItemRepository.delete(orderItem);
        return orderRepository.getReferenceById(orderId);
    }

    @Override
    public Order updateOrderStatus(Order order, OrderStatus orderStatus) {
        if (orderRepository.getReferenceById(order.getId()).getId() != null) {
            Order update = orderRepository.getReferenceById(order.getId());
            update.setStatus(orderStatus);
            return orderRepository.save(update);
        } else {
            throw new RuntimeException("order khong ton tai");
        }
    }

    @Override
    public List<Order> findOrdersBetween(LocalDateTime fromDateTime, LocalDateTime toDateTime) {

        return orderRepository.findByOrderDateTimeBetween(fromDateTime, toDateTime);
    }

    @Override
    public List<Order> findWaitingApprovalOrders() {
        return orderRepository.findByStatus(OrderStatus.WAITING_APPROVAL);
    }

    @Override
    public List<Order> findOrdersByOrderStatus(OrderStatus orderStatus) {

        return orderRepository.findByStatus(orderStatus);
    }

    @Override
    public List<Order> findOrdersByCustomer(Customer customer) {
        return orderRepository.findByCustomer_Mobile(customer.getMobile());
    }
}
