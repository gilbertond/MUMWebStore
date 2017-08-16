///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package edu.mum.amqpServer;
//
//import org.springframework.amqp.core.AmqpTemplate;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.ComponentScan;
//import org.springframework.amqp.core.Queue;
//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
//import org.springframework.amqp.remoting.service.AmqpInvokerServiceExporter;
//import org.springframework.boot.SpringApplication;
//import org.springframework.context.annotation.Bean;
//
///**
// *
// * @author gilberto
// */
//@Configuration
//@ComponentScan
//@EnableAutoConfiguration
//public class ConfigServer {
//
//    @Bean
//    AmqpService amqpServerService() {
//        return new AmqpServiceImpl();
//    }
//    
//    @Bean
//    Queue queue() {
//        return new Queue("messageQ");
//    }
//
//    @Bean
//    AmqpInvokerServiceExporter exporter(AmqpService amqpServerService, AmqpTemplate template) {
//        AmqpInvokerServiceExporter exporter = new AmqpInvokerServiceExporter();
//        exporter.setServiceInterface(AmqpService.class);
//        exporter.setService(amqpServerService);
//        exporter.setAmqpTemplate(template);
//        return exporter;
//    }
//    
//    @Bean
//    SimpleMessageListenerContainer listener(ConnectionFactory factory, AmqpInvokerServiceExporter exporter, Queue queue) {
//        SimpleMessageListenerContainer container = new SimpleMessageListenerContainer(factory);
//        container.setMessageListener(exporter);
//        container.setQueueNames(queue.getName());
//        return container;
//    }
//
//    public static void main(String[] args) {
//        SpringApplication.run(ConfigServer.class, args);
//    }
//}
