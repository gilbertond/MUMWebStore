package edu.mum.service;

import edu.mum.domain.Product;
import edu.mum.domain.ProductCategory;

import java.util.List;


public interface ProductService {

    public void addProduct(Product product);
    public void updatProduct(Product product);
    public List<Product> findAll();
    public List<Product>findByCategoryId(Long productCategory);
    public Product findByProductId(Long productId);
}
