/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.authentication;

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
            throw new UsernameNotFoundException("User not found");
        }
        List<String> roles = crudRepositoryService.findByRolesByEmail(string);
        
        return new UserAccountTemplate(userDetail, roles);
    }    
}
