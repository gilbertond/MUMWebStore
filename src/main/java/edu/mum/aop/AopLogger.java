/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.aop;

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
    
    @Before("execution(public boolean edu.mum.service.TopCostlyReportGenerator.run())")
    public void logServiceStart(){
        System.out.println("**********************************************************\n\n");
        LOG.info("A report to generate product report has been started by a background job...");
        System.out.println("\n\n");
    }
    
//    @Around("execution(public * edu.mum.service.ServiceLayerImpl.fetchRecord(..))")
    public void aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable{
        System.out.println("\n\n");
        LOG.info("Before: A report to generate product report has been started by a background job...");
        joinPoint.proceed();
        LOG.info("After: A report to generate product report has been started by a background job...");
        System.out.println("\n\n");
    }
}
