package edu.mum.service.impl;

import edu.mum.domain.OrderEntity;
import edu.mum.domain.OrderItems;
import edu.mum.repository.OrderRepository;
import edu.mum.repository.ProductRepository;
import edu.mum.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Hatake on 8/12/2017.
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    ProductRepository productRepository;

    public OrderItems setOrderItem(Long id, int qty){
        return new OrderItems(productRepository.findOne(id),qty);
    }

    @Override
    public void checkOut(OrderEntity orderEntity) {
        orderRepository.save(orderEntity);
    }
}
