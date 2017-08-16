package edu.mum.service;

import edu.mum.domain.UserDetail;

/**
 * Created by Hatake on 8/12/2017.
 */
public interface UserService {
    public UserDetail findByEmail(String email);
}
