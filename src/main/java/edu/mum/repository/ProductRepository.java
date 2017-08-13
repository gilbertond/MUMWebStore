package edu.mum.repository;

import edu.mum.domain.Product;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Hatake on 8/12/2017.
 */
public interface ProductRepository extends CrudRepository<Product,Long> {
}
