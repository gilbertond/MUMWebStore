package edu.mum.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by Hatake on 8/11/2017.
 */
@RestController
public class test {

    @RequestMapping("/test")
    public String helloWorld(){
        return "Hello from Spring Boot";
    }
}
