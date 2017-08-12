package mum.edu.Domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Hatake on 8/11/2017.
 */
@Entity
public class Order {
    @Id
    @GeneratedValue
    private Long orderId;
    private String orderNumber;
    private Date dateCreated;
    @OneToOne
    @JoinColumn(name = "userId")
    private UserDetail userDetail;
    @OneToOne
    @JoinColumn(name = "addressId")
    private Address shippingAddress;
    @OneToMany(mappedBy = "order")
    private List<OrderItems> orderItems = new ArrayList<>();
    @Enumerated(EnumType.STRING)
    private OrderStatus orderStatus;

    public Order() {
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
        return dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

  /*  public UserDetail getUserDetail() {
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
    }*/

    public List<OrderItems> getOrderItems() {
        return orderItems;
    }

    public void setOrderItems(List<OrderItems> orderItems) {
        this.orderItems = orderItems;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }
}
