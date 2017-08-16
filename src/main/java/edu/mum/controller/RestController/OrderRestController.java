package edu.mum.controller.RestController;

import edu.mum.domain.OrderEntity;
import edu.mum.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Created by Hatake on 8/16/2017.
 */
@RestController
@RequestMapping("/orderEntity")
public class OrderRestController {

    @Autowired
    private OrderService orderService;
    @GetMapping("/getOrderEntities")
    public List<OrderEntity> getAllOrderEntities(){
        return orderService.getOrderEntities();
    }
}
