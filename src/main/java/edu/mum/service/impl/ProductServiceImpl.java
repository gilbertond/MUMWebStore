package edu.mum.service.impl;

import edu.mum.dao.ProductRepository;
import edu.mum.domain.Product;
import edu.mum.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    ProductRepository productRepository;
    @Override
    @Transactional
    public void addProduct(Product product) {
       // product.getCategory().setCategoryId(catagoryId);
        productRepository.save(product);
    }

    @Override
    @Transactional
    public void updatProduct(Product product) {
       // product.getCategory().setCategoryId(catagoryId);
        productRepository.save(product);

    }

    @Override
    @Transactional
    public List<Product> findAll() {
        List<Product>products = new ArrayList<>();
        products.addAll(productRepository.findAll());
        return products;
    }

    @Override
    @Transactional
    public List<Product> findByCategoryId(Long productCategory) {
        List<Product>productList = new ArrayList<>();
        productList.addAll(productRepository.findByCategory_CategoryId(productCategory));
        return productList;
    }

    @Override
    @Transactional
    public Product findByProductId(Long productId) {
        Product product = productRepository.findOne(productId);
        return product;
    }


}
