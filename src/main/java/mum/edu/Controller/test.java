package mum.edu.Controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
