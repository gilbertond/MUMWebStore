package edu.mum.controller;

import edu.mum.domain.*;
import edu.mum.service.OrderService;
import edu.mum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpSession;
import java.security.Principal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Hatake on 8/12/2017.
 */
@Controller
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;


    @RequestMapping(value = "/addToCart", method = RequestMethod.POST)
    public String orderNow(Long id, int qty, HttpSession session) {
        if (session.getAttribute("cart") == null) {
            List<OrderItems> orderItems = new ArrayList<>();
            session.setAttribute("cart", orderItems);
        }
        List<OrderItems> orderItems = (List<OrderItems>) session.getAttribute("cart");
        OrderItems orderItems1 = orderService.setOrderItem(id, qty);
        orderItems.add(orderItems1);
        session.setAttribute("cart", orderItems);

        return "redirect:/cartPage";
    }

    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public String deleteCart(Long id, int qty, HttpSession session) {
        List<OrderItems> orderItems = (List<OrderItems>) session.getAttribute("cart");
        orderItems = orderItems.stream().filter(orderItems1 -> !(orderItems1.getProduct().getProductId().equals(id) && orderItems1.getQuantity() == qty)).collect(Collectors.toList());
        session.setAttribute("cart", orderItems);
        return "redirect:/cartPage";
    }

    @RequestMapping(value = "/checkout",method = RequestMethod.GET)
    public String checkout(@ModelAttribute Address shippingAddress, Principal principal, HttpSession session){
        Date date = new Date();
        UserDetail userDetail = userService.findByEmail(principal.getName());
        shippingAddress.setUserDetail(userDetail);
        OrderStatus orderStatus = OrderStatus.NEW;
        List<OrderItems> orderItems = (List<OrderItems>) session.getAttribute("cart");
        OrderEntity orderEntity=new OrderEntity(date,userDetail,shippingAddress,orderStatus,orderItems);
        orderService.checkOut(orderEntity);
        session.removeAttribute("cart");
        return "successPage";
    }

}
