package edu.mum.dao;

import edu.mum.domain.ProductCategory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


public interface CatagoryRepository extends JpaRepository<ProductCategory,Long> {


}
