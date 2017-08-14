package edu.mum.controller;

import edu.mum.domain.ProductCategory;
import edu.mum.service.CatagoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class CatagoryController {
    @Autowired
    CatagoryService catagoryService;

    @RequestMapping("/addCatagoryForm")
    public String catagoryForm(){
        return "/catagory";
    }

    @RequestMapping(value = "/addCatagory", method = RequestMethod.POST)
    public String addCatagory(ProductCategory productCategory){
        catagoryService.addCatagory(productCategory);
//        System.out.println("AASAJBADN<MAH");
        return "redirect:/addCatagoryForm";
    }

}
