package edu.mum.service.impl;

import edu.mum.dao.IUserCrudRepositoryService;
import edu.mum.domain.UserDetail;
import edu.mum.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Hatake on 8/12/2017.
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private IUserCrudRepositoryService crudRepositoryService;

    @Override
    public UserDetail findByEmail(String email) {
        return crudRepositoryService.findByEmail(email);
    }
}
