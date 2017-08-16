package edu.mum.domain;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import java.io.Serializable;
import java.util.Arrays;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by Hatake on 8/11/2017.
 */
@Entity
@Table(name = "product")
@XmlRootElement
public class Product implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long productId;
    @Column(name = "productName")
    private String productName;
    @ManyToOne
    @JoinColumn(name = "categoryId")
    @JsonManagedReference
    private ProductCategory category;

//    @Override
//    public String toString() {
//        return "Product{" +
//                "productId=" + productId +
//                ", productName='" + productName + '\'' +
//                ", category=" + category +
//                ", cost=" + cost +
//                ", quantityAvailable=" + quantityAvailable +
//                ", image=" + Arrays.toString(image) +
//                ", description='" + description + '\'' +
//                '}';
//    }

    @Column(name = "cost")
    private Double cost;
    @Column(name = "quantityAvailable")
    private Integer quantityAvailable;
    @Column(name="image")
    private String image;
    @Column(name = "description")
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

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
