///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package edu.mum.configuration;
//
//import java.util.Properties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.JavaMailSenderImpl;
//
///**
// *
// * @author gilberto
// */
//@Configuration
//public class EmailUtility {
//
//    @Bean
//    public JavaMailSender createMailSender() {
//        JavaMailSender javaMailSender = new JavaMailSenderImpl();
//        javaMailSender.setHost("smtp.gmail.com");
//        javaMailSender.setPort(587);
//
//        javaMailSender.setUsername("my.gmail@gmail.com");
//        javaMailSender.setPassword("password");
//
//        Properties props = javaMailSender.getJavaMailProperties();
//        props.put("mail.transport.protocol", "smtp");
//        props.put("mail.smtp.auth", "true");
//        props.put("mail.smtp.starttls.enable", "true");
//        props.put("mail.debug", "true");
//
//        return javaMailSender;
//    }
//}
