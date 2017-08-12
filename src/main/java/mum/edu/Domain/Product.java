package mum.edu.Domain;

import javax.persistence.*;

/**
 * Created by Hatake on 8/11/2017.
 */
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long productId;
    private String productName;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    private ProductCategory category;
    private Double cost;
    private Integer quantityAvailable;
    private byte[] image;
    private String description;

    public Product() {
    }

    public Long getProductId() {

        return productId;
    }

    public void setProductId(Long productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public ProductCategory getCategory() {
        return category;
    }

    public void setCategory(ProductCategory category) {
        this.category = category;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public Integer getQuantityAvailable() {
        return quantityAvailable;
    }

    public void setQuantityAvailable(Integer quantityAvailable) {
        this.quantityAvailable = quantityAvailable;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
