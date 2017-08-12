package mum.edu.Domain;

import javax.persistence.*;

/**
 * Created by Hatake on 8/11/2017.
 */
@Entity
public class OrderItems {
    @Id
    @GeneratedValue
    private Long orderItemId;
    @ManyToOne
    @JoinColumn(name = "orderId")
    private Order order;
    @OneToOne
    @JoinColumn(name = "productId")
    private Product product;
    private Integer quantity;

    public Long getOrderItemId() {
        return orderItemId;
    }

    public void setOrderItemId(Long orderItemId) {
        this.orderItemId = orderItemId;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
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
}
