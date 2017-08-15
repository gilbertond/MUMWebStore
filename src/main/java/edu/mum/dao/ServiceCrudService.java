/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.mum.dao;

import edu.mum.domain.Product;
import edu.mum.domain.Services;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author gilberto
 */
@Repository
public interface ServiceCrudService extends CrudRepository<Services, Integer>{
        
}
