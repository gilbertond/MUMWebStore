package edu.mum.service;

import edu.mum.domain.OrderEntity;
import edu.mum.domain.OrderItems;

/**
 * Created by Hatake on 8/12/2017.
 */
public interface OrderService {
    public OrderItems setOrderItem(Long id, int qty);
    public void checkOut(OrderEntity orderEntity);
}
