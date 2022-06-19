package vn.fis.training.ordermanagement.domain;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@NamedQuery(name = "Order.findByOrderDateTimeBetween",
        query = "select o from Order o where o.orderDateTime between ?1 and ?2")
@Table(name = "tbl_order")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "order_datetime")
    private LocalDateTime orderDateTime;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems;

    @Column(name = "total_amount")
    private Double totalAmount;

    /**
     * Bao gom cac trang thai duoc dinh nghia trong OrderStatus Class
     */
    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    public Order(Long id, LocalDateTime orderDateTime, Customer customer, List<OrderItem> orderItems, Double totalAmount, OrderStatus status) {
        this.id = id;
        this.orderDateTime = orderDateTime;
        this.customer = customer;
        this.orderItems = orderItems;
        this.totalAmount = totalAmount;
        this.status = status;
    }

    public Order() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getOrderDateTime() {
        return orderDateTime;
    }

    public void setOrderDateTime(LocalDateTime orderDateTime) {
        this.orderDateTime = orderDateTime;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<OrderItem> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItem> orderItems) {
        this.orderItems = orderItems;
    }

    public Double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public OrderStatus getStatus() {
        return status;
    }

    public void setStatus(OrderStatus status) {
        this.status = status;
    }

}
