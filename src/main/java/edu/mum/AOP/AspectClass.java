package edu.mum.AOP;

import edu.mum.domain.UserDetail;
import edu.mum.mailservice.MailUtil;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by Hatake on 8/15/2017.
 */
@Component
@Aspect
public class AspectClass {
    @Autowired
    private MailUtil mailUtil;

    @After("execution(public void edu.mum.service.OrderService.checkOut(..))")
    public void emailSender(){
        System.out.println("Hello from Aspect");
        mailUtil.sendEmail("stonehunter02@gmail.com","sumankarki465@gmail.com","From AOP","Congratulations you have successfully checked out!!");
    }
}
