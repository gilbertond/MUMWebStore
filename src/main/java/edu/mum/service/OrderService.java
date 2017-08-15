package edu.mum.service;

import edu.mum.domain.*;

import java.util.Date;
import java.util.List;

/**
 * Created by Hatake on 8/12/2017.
 */
public interface OrderService {
    public OrderItems setOrderItem(Long id, int qty);
    public void checkOut(Date date, UserDetail userDetail, Address shippingAddress, OrderStatus orderStatus,List<OrderItems> orderItems);
}
