package com.nguyendoha.Test_final_fis.service.Imp;

import com.nguyendoha.Test_final_fis.controller.ExceptionHandler.CustomerNotFoundException;
import com.nguyendoha.Test_final_fis.controller.ExceptionHandler.OrderExceptionNotSupport;
import com.nguyendoha.Test_final_fis.controller.ExceptionHandler.ProductOutStockException;
import com.nguyendoha.Test_final_fis.dto.OrderDto;
import com.nguyendoha.Test_final_fis.dto.OrderItemDto;
import com.nguyendoha.Test_final_fis.model.*;
import com.nguyendoha.Test_final_fis.repository.CustomerRepository;
import com.nguyendoha.Test_final_fis.repository.OrderItemRepository;
import com.nguyendoha.Test_final_fis.repository.OrderRepository;
import com.nguyendoha.Test_final_fis.repository.ProductRepository;
import com.nguyendoha.Test_final_fis.service.OrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class OrderServiceImp implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderItemRepository orderItemRepository;

    @Transactional
    @Override
    public Order Create(OrderDto orderDto) {
        // thêm order kèm 1 list orderitem cua order hien tai
        Optional<Customer> customer = customerRepository.findById(orderDto.getCustomer());
        if (customer.isPresent()) {
            Order order = Order.builder()
                    .totalAmount(0d)
                    .status(orderDto.getStatus())
                    .customer(customer.get())
                    .orderDateTime(LocalDateTime.now())
                    .build();
            Order orders = orderRepository.save(order);
            List<OrderItem> orderItems = new ArrayList<>();
            orderDto.getOrderItems().stream().forEach(orderItemDto -> {
                OrderItem item = OrderItem.builder()
                        .order(orders)
                        .amount(productRepository.findById(orderItemDto.getProduct()).get().getPrice() * orderItemDto.getQuantity())
                        .product(productRepository.findById(orderItemDto.getProduct()).get())
                        .quantity(orderItemDto.getQuantity())
                        .build();
                orders.setTotalAmount(order.getTotalAmount() + item.getAmount());
                orderItems.add(item);
                Product product = productRepository.findById(orderItemDto.getProduct()).get();
                if (product.getAvailable() == 0 || product.getAvailable() < orderItemDto.getQuantity()) {
                    log.info("\n product it out of stock " + "\n Time : " + LocalDateTime.now());
                    throw new ProductOutStockException("The product is out of stock");
                }
                product.setAvailable(product.getAvailable() - orderItemDto.getQuantity());
                productRepository.save(product);
            });
            orders.setOrderItems(orderItems);
            return orderRepository.save(orders);
        } else {
            log.error("\n save false" + "\n Time : " + LocalDate.now() + "\n orderDto : " + orderDto);
            throw new CustomerNotFoundException("Customer does not exist");
        }
    }

    @Transactional(readOnly = true)
    @Override
    public Page<Order> findAll(Pageable pageable) {
        return orderRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    @Override
    public Optional<Order> findById(Long id) {
        return Optional.ofNullable(orderRepository.findById(id).orElseThrow(
                () -> {
                    throw new CustomerNotFoundException(String.format("Not found Order with id %s", id));
                }));
    }

    @Transactional
    @Override
    public void delete(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.get().getStatus().equals(OrderStatus.CREATED) ||
                order.get().getStatus().equals(OrderStatus.CANCELLED)) {
            orderRepository.deleteById(id);
        } else {
            log.error("\n delete false" + "\n Time : " + LocalDate.now() + "\n orderId : " + id);
            throw new OrderExceptionNotSupport("order not CREATED ,CANCELLED ");
        }
    }

    @Transactional
    @Override
    public Order addOrderItem(OrderItemDto orderItemDto) {
        Optional<Order> order = orderRepository.findById(orderItemDto.getOrder());
        Optional<Product> product = productRepository.findById(orderItemDto.getProduct());
        if (order.isPresent() && product.isPresent() && order.get().getStatus().equals(OrderStatus.CREATED)) {
            if (product.get().getAvailable() == 0 || product.get().getAvailable() < orderItemDto.getQuantity()) {
                log.info("\n product it out of stock " + "\n Time : " + LocalDateTime.now());
                throw new ProductOutStockException("The product is out of stock");
            }
            product.get().setAvailable(product.get().getAvailable() - orderItemDto.getQuantity());
            productRepository.save(product.get());
            OrderItem orderItem = OrderItem.builder()
                    .quantity(orderItemDto.getQuantity())
                    .order(order.get())
                    .product(product.get())
                    .amount(product.get().getPrice() * orderItemDto.getQuantity())
                    .build();
            order.get().getOrderItems().add(orderItem);
            order.get().setTotalAmount(order.get().getTotalAmount()
                    + orderItem.getAmount());
            return orderRepository.save(order.get());
        } else {
            log.error("\n add OrderItem false" + "\n Time : " + LocalDate.now() + "\n OrderItem : " + orderItemDto);
            throw new OrderExceptionNotSupport("order or product does not exist or order not CREATED");
        }
    }

    @Transactional
    @Override
    public Order removeOrderItem(OrderItemDto orderItemDto) {
        Optional<OrderItem> orderItem = orderItemRepository.findById(orderItemDto.getId());
        Optional<Order> order = orderRepository.findById(orderItem.get().getOrder().getId());
        Optional<Product> product = productRepository.findById(orderItem.get().getProduct().getId());
        if (order.get().getStatus().equals(OrderStatus.CREATED)) {
            orderItemRepository.deleteById(orderItemDto.getId());
            // khách hàng không muốn mua món hàng , họ đổi sản phẩm khác , số lượng sản phẩm dc hoàn trả lại !!!
            order.get().setTotalAmount(order.get().getTotalAmount() -
                    (product.get().getPrice() * orderItem.get().getQuantity()));
            product.get().setAvailable(orderItem.get().getQuantity() + product.get().getAvailable());
            productRepository.save(product.get());
            return orderRepository.save(order.get());
        } else {
            log.error("\n remove OrderItem false" + "\n Time : " + LocalDate.now() + "\n OrderItem : " + orderItemDto);
            throw new OrderExceptionNotSupport("order does not exist or order not CREATED");
        }
    }

    @Transactional
    @Override
    public Order updateStatusPaid(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent() && order.get().getStatus().equals(OrderStatus.CREATED)) {
            order.get().setStatus(OrderStatus.PAID);
            return orderRepository.save(order.get());
        } else {
            log.error("\n update Status Paid false" + "\n Time : " + LocalDate.now() + "\n orderId : " + id);
            throw new OrderExceptionNotSupport("order does not exist or order not CREATED");
        }
    }

    @Transactional
    @Override
    public Order updateStatusCancel(Long id) {
        Optional<Order> order = orderRepository.findById(id);
        if (order.isPresent() && order.get().getStatus().equals(OrderStatus.CREATED)) {
            order.get().setStatus(OrderStatus.CANCELLED);
            return orderRepository.save(order.get());
        } else {
            log.error("\n update status cancel false" + "\n Time : " + LocalDate.now() + "\n OrderId : " + id);
            throw new OrderExceptionNotSupport("order does not exist or order not CREATED");
        }
    }
}
