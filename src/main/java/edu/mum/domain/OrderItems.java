package edu.mum.domain;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.*;

/**
 * Created by Hatake on 8/11/2017.
 */
@Entity
@Table(name = "orderItems")
public class OrderItems implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long orderItemId;
    @ManyToOne
    //@JoinColumn(name = "orderId")
    private OrderEntity order;
    @OneToOne
    @JoinColumn(name = "productId")
    private Product product;
    @Column(name = "quantity")
    private Integer quantity;

    public OrderItems() {
    }

    public OrderItems(Product product, Integer quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    @Override
    public int hashCode() {
        int hash = 17;
        hash = 31 * hash + Objects.hashCode(this.orderItemId);
        hash = 31 * hash + Objects.hashCode(this.order);
        hash = 31 * hash + Objects.hashCode(this.product);
        hash = 31 * hash + Objects.hashCode(this.quantity);
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
        final OrderItems other = (OrderItems) obj;
        if (!Objects.equals(this.orderItemId, other.getOrderItemId())) {
            return false;
        }
        if (!Objects.equals(this.order, other.getOrder())) {
            return false;
        }
        if (!Objects.equals(this.product, other.getProduct())) {
            return false;
        }
        if (!Objects.equals(this.quantity, other.getQuantity())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "OrderItems{" + "order=" + order.getOrderNumber() + ", product=" + product + ", quantity=" + quantity + '}';
    }
}
