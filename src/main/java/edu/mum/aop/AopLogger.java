/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.aop;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

/**
 *
 * @author gilberto
 */

@Aspect
@Component
public class AopLogger {
    private static final Logger LOG = Logger.getLogger(AopLogger.class.getName());
    
    @Before("execution(public void edu.mum.service.TopCostlyReportGenerator.run())")
    public void logServiceStart(){
        System.out.println("**********************************************************\n\n");
        LOG.info("A report to generate product report has been started by a background job...");
        System.out.println("\n\n");
    }
    
//    @Around("execution(public * edu.mum.controller.UserController.userSave(HttpServletRequest, RedirectAttributes, Principal) && args(request, redirectAttributes, principal)")
//    public void aroundAdvice(ProceedingJoinPoint joinPoint, HttpServletRequest request, RedirectAttributes redirectAttributes, Principal principal) throws Throwable{
//        System.out.println("\n\n");
//        LOG.info("Before Round: Detected new user creation...");
//        joinPoint.proceed();
//        LOG.info("After Round: A report to generate product report has been started by a background job...");
//        System.out.println("\n\n");
//    }
    
    @Around("execution(public void edu.mum.service.TopCostlyReportGenerator.run())")
    public void aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("\n\n");
        DateFormat format = new SimpleDateFormat("MMM dd, YYYY h:mm:ss a");
        LOG.log(Level.INFO, "Before: A report to generate product report has been started by a background job...{0}", format.format(new Date()));
        joinPoint.proceed();
        LOG.log(Level.INFO, "After: A report to generate product report has been started by a background job...{0}", format.format(new Date()));
        System.out.println("\n\n");
    }
}
