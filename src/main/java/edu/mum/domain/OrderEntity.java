package edu.mum.domain;

import javax.persistence.*;
import java.io.Serializable;
import java.util.*;

/**
 * Created by Hatake on 8/11/2017.
 */
@Entity
@Table(name = "orderentity")
public class OrderEntity implements Serializable {
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
    @OneToMany(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "orderId")
    private List<OrderItems> orderItems;
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public OrderEntity() {
        this.orderItems = new ArrayList<>();
    }

    public OrderEntity(Date dateCreated, String orderNo, UserDetail userDetail, Address shippingAddress, OrderStatus orderStatus, List<OrderItems> orderItems) {
        this.orderNumber = orderNo;
        this.dateCreated = dateCreated;
        this.userDetail = userDetail;
        this.shippingAddress = shippingAddress;
        this.orderItems = orderItems;
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
        final OrderEntity other = (OrderEntity) obj;
        if (!Objects.equals(this.orderId, other.getOrderId())) {
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
        return "Order{" + "orderNumber=" + orderId + ", dateCreated=" + dateCreated + ", shippingAddress=" + shippingAddress +
                ", orderItems=" + orderItems.size();// + ", orderStatus=" + orderStatus + '}';
    }
}
