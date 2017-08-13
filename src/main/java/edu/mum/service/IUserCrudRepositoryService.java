/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.service;

import edu.mum.domain.Role;
import edu.mum.domain.UserDetail;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

/**
 *
 * @author 985939
 */
@Repository("crudRepositoryService")
public interface IUserCrudRepositoryService extends CrudRepository<UserDetail, Long>{
    public UserDetail findByEmail(String email);
    
//    @Query("SELECT ur FROM UserDetail u JOIN FETCH u.roles ur WHERE u.email=?")
//    public List<Role> findByRolesByEmail(String email);
}
