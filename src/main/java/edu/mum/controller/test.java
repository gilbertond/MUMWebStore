package edu.mum.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Hatake on 8/11/2017.
 */
@Controller
public class test {

    @RequestMapping("/test")
    public String helloWorld(){
        return "/addProduct";
    }
}
