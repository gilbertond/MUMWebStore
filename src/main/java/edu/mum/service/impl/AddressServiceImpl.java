package edu.mum.service.impl;

import edu.mum.dao.AddressRepository;
import edu.mum.domain.Address;
import edu.mum.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by Hatake on 8/14/2017.
 */
@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressRepository addressRepository;

    @Override
    public void saveAddress(Address address) {
        addressRepository.save(address);
    }
}
