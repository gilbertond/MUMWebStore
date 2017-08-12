package mum.edu.Domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;

/**
 * Created by Hatake on 8/11/2017.
 */
@Entity
@Table(name = "order")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderId;
    @Column(name = "orderNumber")
    private String orderNumber;
    @Temporal(TemporalType.TIMESTAMP)
    private Date dateCreated;
    @OneToOne
    @JoinColumn(name = "userId")
    private UserDetail userDetail;
    @OneToOne
    @JoinColumn(name = "addressId")
    private Address shippingAddress;
    @OneToMany(mappedBy = "order", cascade = CascadeType.PERSIST)
    private List<OrderItems> orderItems;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public Order() {
        this.orderItems = new ArrayList<>();
    }

    public Order(String orderNumber, Date dateCreated, UserDetail userDetail, Address shippingAddress, OrderStatus orderStatus) {
        this.orderNumber = orderNumber;
        this.dateCreated = dateCreated;
        this.userDetail = userDetail;
        this.shippingAddress = shippingAddress;
        this.orderItems = new ArrayList<>();
        this.orderStatus = orderStatus;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Date getDateCreated() {
        return (Date) dateCreated.clone();
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = (Date) dateCreated.clone();
    }

    public List<OrderItems> getOrderItems() {
        return Collections.unmodifiableList(orderItems);
    }

    public void addOrderItems(List<OrderItems> orderItems) {
        this.orderItems.addAll(orderItems);
    }

    public void addOrderItem(OrderItems orderItem) {
        this.orderItems.add(orderItem);
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public UserDetail getUserDetail() {
        return userDetail;
    }

    public void setUserDetail(UserDetail userDetail) {
        this.userDetail = userDetail;
    }

    public Address getShippingAddress() {
        return shippingAddress;
    }

    public void setShippingAddress(Address shippingAddress) {
        this.shippingAddress = shippingAddress;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + Objects.hashCode(this.orderId);
        hash = 31 * hash + Objects.hashCode(this.orderNumber);
        hash = 31 * hash + Objects.hashCode(this.dateCreated);
        hash = 31 * hash + Objects.hashCode(this.userDetail);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (!Objects.equals(this.orderId, other.getOrderId())) {
            return false;
        }
        if (!Objects.equals(this.orderNumber, other.getOrderNumber())) {
            return false;
        }
        if (!Objects.equals(this.dateCreated, other.getDateCreated())) {
            return false;
        }
        if (!Objects.equals(this.userDetail, other.getUserDetail())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "orderNumber=" + orderNumber + ", dateCreated=" + dateCreated + ", shippingAddress=" + shippingAddress + ", orderItems=" + orderItems.size() + ", orderStatus=" + orderStatus + '}';
    }
}
