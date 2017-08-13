/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.authentication;

import edu.mum.domain.Role;
import edu.mum.domain.UserDetail;
import edu.mum.service.IUserCrudRepositoryService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 *
 * @author 985939
 */
@Service("userDetailsService")
public class UserDetailsBuilder implements UserDetailsService{

    private final IUserCrudRepositoryService crudRepositoryService;

    @Autowired
    UserDetailsBuilder(IUserCrudRepositoryService service){
        this.crudRepositoryService = service;
    }
    
    @Override
    public UserDetails loadUserByUsername(String string) throws UsernameNotFoundException {
        UserDetail userDetail = crudRepositoryService.findByEmail(string);
        if(userDetail == null){
            
            //just for testing.....
            if(string.equals("root@emal.com")){
                userDetail = new UserDetail("TestF", "TestL", "root@emal.com", "$2a$04$4Evubhc3m6xSC2XMfjHjqeeEP4bbWzHD/tfUlGT5Q1W7dis.HUhH6", Boolean.TRUE);
                userDetail.addRole(Role.ROOT);
                return new UserAccountTemplate(userDetail, userDetail.getRoles());
            }
            //just for testing.....
            
            
            throw new UsernameNotFoundException("User not found");
        }
        List<Role> roles = userDetail.getRoles(); //crudRepositoryService.findByRolesByEmail(string);
        
        return new UserAccountTemplate(userDetail, roles);
    }    
}
