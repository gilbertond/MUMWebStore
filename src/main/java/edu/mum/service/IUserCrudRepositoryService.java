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
import org.springframework.stereotype.Service;

/**
 *
 * @author 985939
 */
@Service
public interface IUserCrudRepositoryService extends CrudRepository<UserDetail, Long>{
    public UserDetail findByEmail(String email);
    
    @Query("SELECT ur.userrole FROM user_detail_roles ur, userdetail u WHERE ur.user_detail_user_id=u.user_id AND u.email=?")
    public List<String> findByRolesByEmail(String email);
}
