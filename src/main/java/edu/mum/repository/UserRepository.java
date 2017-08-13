package edu.mum.repository;

import edu.mum.domain.UserDetail;
import org.springframework.data.repository.CrudRepository;

/**
 * Created by Hatake on 8/12/2017.
 */
public interface UserRepository extends CrudRepository<UserDetail,Long> {
    public UserDetail findByEmail(String email);
}
