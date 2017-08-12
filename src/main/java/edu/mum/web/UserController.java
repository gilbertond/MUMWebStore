/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author 985939
 */
@Controller
@RequestMapping(value = "/user")
public class UserController {
    @RequestMapping()
    public String getMain(){
        return "/login";
    }
}
