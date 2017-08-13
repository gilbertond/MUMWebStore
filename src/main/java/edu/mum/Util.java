/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum;

import edu.mum.service.IUserCrudRepositoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.stereotype.Component;

/**
 *
 * @author gilberto
 */
@Component
public class Util implements ApplicationListener<ContextRefreshedEvent> {

    @Autowired
    IUserCrudRepositoryService crudRepositoryService;
    
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {
        
    }
}
