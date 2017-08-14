package edu.mum.controller;

import edu.mum.domain.Product;
import edu.mum.service.CatagoryService;
import edu.mum.service.ProductService;
import org.hibernate.boot.jaxb.SourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Optional;

@Controller
public class ProductController {

    @Autowired
    private ServletContext servletContext;

    @Autowired
    ProductService productService;

    @Autowired
    CatagoryService catagoryService;

    @RequestMapping("/index")
    public String productForm(Model model, @RequestParam("categoryId") Optional<Long> categoryId){
        if(categoryId.isPresent()){
            model.addAttribute("products",productService.findByCategoryId(categoryId.get()));
        }else {
            model.addAttribute("products",productService.findAll());
        }
        model.addAttribute("catagories",catagoryService.getCatagory());
        return "/product";
    }

    @RequestMapping(value = "/productPage",method = RequestMethod.POST)
    public String beckToHomePage(){
        return "redirect:/index";
    }

    @RequestMapping("/productDescriptionForm")
    public String productDescriptionForm(Model model,@RequestParam("id") Optional<Long> productId){
        model.addAttribute("product",productService.findByProductId(productId.get()));
        return "/productDescription";
    }

    @RequestMapping("/addProductForm")
    public String catagoryForm( Model model){
        model.addAttribute("catagories",catagoryService.getCatagory());
        return "/addProduct";
    }

    @RequestMapping(value = "/addProduct", method = RequestMethod.POST)
    public String addCatagory(Product product, Long catagoryId, MultipartFile imageName) throws IOException {
        product.setCategory(catagoryService.findOne(catagoryId));
        String fileName = "";
        if(imageName != null) {
            byte[] bytes = imageName.getBytes();
            fileName = imageName.getOriginalFilename();
            String fileLocation = new File("src\\main\\resources\\static\\images").getAbsolutePath() + "\\" + fileName;
            FileOutputStream fos = new FileOutputStream(fileLocation);
            fos.write(bytes);
            fos.close();
        }
        product.setImage(fileName);
        System.out.println("hello");
        productService.updatProduct(product);
        return "redirect:/addProductForm";
    }
}
