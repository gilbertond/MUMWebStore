package edu.mum.dao;

import edu.mum.domain.OrderEntity;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Hatake on 8/12/2017.
 */
public interface OrderRepository extends CrudRepository<OrderEntity,Long> {

}
