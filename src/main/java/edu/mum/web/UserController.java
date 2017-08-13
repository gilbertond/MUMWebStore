/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.web;

import java.security.Principal;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author 985939
 */
@Controller
public class UserController {
//    @RequestMapping(value = "/login")
//    public String getHome(){
//        return "/login";
//    }
    
//    @RequestMapping(value = "login", method = RequestMethod.POST)
//    public String getMain(){
//        return "/main";
//    }
    
    @RequestMapping(value = "/user")
    public String getLogin(Principal principal){
        if (principal == null) {
            return "redirect:login";
        }
        return "/menu2";
    }
    
    @RequestMapping(value = "/signup")
    public String getSignup(){
        return "/signup";
    }
    
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String getSignup(HttpServletRequest request){
        
        return "/signup";
    }
}
