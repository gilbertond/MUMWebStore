package edu.mum.service.impl;

import edu.mum.dao.AddressRepository;
import edu.mum.dao.ProductRepository;
import edu.mum.domain.*;
import edu.mum.dao.OrderRepository;
import edu.mum.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;

/**
 * Created by Hatake on 8/12/2017.
 */
@Service
@Transactional
public class OrderServiceImpl implements OrderService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Override
    public OrderItems setOrderItem(Long id, int qty){
        return new OrderItems(productRepository.findOne(id),qty);
    }

    @Override
    @Transactional
    public void checkOut(Date date, UserDetail userDetail, Address shippingAddress, OrderStatus orderStatus, List<OrderItems> orderItems) {
        addressRepository.save(shippingAddress);
        OrderEntity orderEntity = new OrderEntity(date, userDetail, shippingAddress, orderStatus,orderItems);
        orderRepository.save(orderEntity);
    }
}
