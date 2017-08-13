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
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
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
                BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
                userDetail = new UserDetail("TestF", "TestL", "root@emal.com", bCryptPasswordEncoder.encode("Test123"), Boolean.TRUE);
                userDetail.addRole(Role.ROLE_ROOT);
                userDetail.addRole(Role.ROLE_USER);
                return new UserAccountTemplate(userDetail, userDetail.getRoles());
            }
            //just for testing.....
            
            
            throw new UsernameNotFoundException("User not found");
        }
        List<Role> roles = userDetail.getRoles(); //crudRepositoryService.findByRolesByEmail(string);
        
        return new UserAccountTemplate(userDetail, roles);
    }    
}
