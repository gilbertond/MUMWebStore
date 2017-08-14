package edu.mum.dao;

import edu.mum.domain.Product;
import edu.mum.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

@Service
public interface ProductRepository extends JpaRepository<Product,Long> {
    public List<Product> findByCategory_CategoryId(Long productCategory);
}
