/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.jaxrs;

import edu.mum.domain.Product;
import edu.mum.service.ProductService;
import java.io.StringWriter;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.PropertyException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author gilberto
 */
@Service
@Path("products")
public class Restfulclass {
    
    @Autowired
    ProductService productService;
    
    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public String returnPlainString(){
        return "Greeting from WebStore Web Service";
    }
    
    @GET
    @Produces(MediaType.TEXT_XML)
    public List<Product> returnStringXML() throws PropertyException, JAXBException {
        List<Product> products = productService.findAll();
        return products;
    }
    
    @GET
    @Produces(MediaType.TEXT_HTML)
    public String returnTextHTML(){
        return "<html> " + "<title>" + "Returned Page" + "</title>"
        + "<body><h1>" + "Greeting from WebStore Web Service" + "</h1></body>" + "</html> ";
    }
    
    // This method is called if XML is request
    @GET
    @Produces({MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON})
    public List<Product> getXML() {
        List<Product> products = productService.findAll();
        return products;
    }
    
    // This can be used to test the integration with the browser
//    @GET
//    @Produces({MediaType.TEXT_XML})
//    public Personclass getHTML() {
//        Personclass personclass = new Personclass();
//        personclass.setFname("Gilbert");
//        personclass.setLname("Ndenzi");
//        personclass.setAge(30);
//        personclass.setGender("Male");
//        return personclass;
//    }
}