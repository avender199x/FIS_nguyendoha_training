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
        Order order = orderRepository.getReferenceById(orderId);
        order.getOrderItems().add(orderItem);
        order.setTotalAmount(order.getTotalAmount() + (orderItem.getAmount() * orderItem.getQuantity()));
        if (orderItemRepository.findById(orderItem.getId()).get().getOrder().getId().equals(orderId)) {
            orderItemRepository.save(orderItem);
        } else {
            throw new RuntimeException("orderItem co dia chi id order khac");
        }
        return orderRepository.save(order);
    }

    @Override
    public Order removeOrderItem(Long orderId, OrderItem orderItem) {
        if (orderRepository.findById(orderId).isPresent() &&
                orderItemRepository.findById(orderItem.getId()).get().getOrder().getId().equals(orderId)) {
            Order order = orderRepository.getReferenceById(orderId);
            order.setTotalAmount(order.getTotalAmount() - (orderItem.getAmount() * orderItem.getQuantity()));
            order.getOrderItems().remove(orderItem);
            if (orderItemRepository.findById(orderItem.getId()).isPresent()) {
                orderItemRepository.delete(orderItem);
            }
            orderRepository.save(order);
        } else {
            throw new RuntimeException("order khong ton tai hoac orderItem khong phai cua order");
        }
        return orderRepository.getReferenceById(orderId);
    }

    @Override
    public Order updateOrderStatus(Order order, OrderStatus orderStatus) {
        if (orderRepository.findById(order.getId()).isPresent()) {
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
