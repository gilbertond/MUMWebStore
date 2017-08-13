/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 *
 * @author 985939
 */
@Configuration
public class WebMvcUtility extends WebMvcConfigurerAdapter {

//    @Bean(name = "dataSource")
//    public DriverManagerDataSource driverManagerDataSource() {
//        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
//        driverManagerDataSource.setDriverClassName("com.mysql.jdbc.Driver");
//        driverManagerDataSource.setUrl("jdbc:mysql://localhost:3306/mumwebstore?createDatabaseIfNotExist=true&useSSL=true");
//        driverManagerDataSource.setUsername("root");
//        driverManagerDataSource.setPassword("");
//
//        return driverManagerDataSource;
//    }
    
    @Bean
    public InternalResourceViewResolver internalResourceViewResolver() {
        InternalResourceViewResolver internalResourceViewResolver = new InternalResourceViewResolver();
        internalResourceViewResolver.setPrefix("/WEB-INF/views/");
        internalResourceViewResolver.setSuffix(".jsp");

        return internalResourceViewResolver;
    }
    
    @Override
    public void addViewControllers(ViewControllerRegistry registry){
        registry.addViewController("/home").setViewName("login");
        registry.addViewController("/").setViewName("login");
//        registry.addViewController("/user").setViewName("main");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/403").setViewName("login");
    }
}
