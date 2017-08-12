package mum.edu.Domain;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.GenerationType;
import javax.persistence.Table;

/**
 * Created by Hatake on 8/11/2017.
 */
@Entity
@Table(name = "productCategory")
public class ProductCategory implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long categoryId;
    @Column(name = "categoryName")
    private String categoryName;
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "category", cascade = CascadeType.PERSIST)
    private List<Product> products;

    public ProductCategory() {
        this.products = new ArrayList<>();
    }

    public ProductCategory(String categoryName, String description) {
        this.products = new ArrayList<>();
        this.categoryName = categoryName;
        this.description = description;
        this.products = new ArrayList<>();
    }    
    
    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Product> getProducts() {
        return Collections.unmodifiableList(products);
    }

    public void addProducts(List<Product> products) {
        this.products.addAll(products);
    }
    
    public void addProduct(Product product) {
        this.products.add(product);
    }
}
