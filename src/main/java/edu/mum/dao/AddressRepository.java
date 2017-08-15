package edu.mum.dao;

import edu.mum.domain.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by Hatake on 8/14/2017.
 */
public interface AddressRepository extends JpaRepository<Address,Long> {
}
