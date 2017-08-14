package edu.mum.service.impl;

import edu.mum.dao.CatagoryRepository;
import edu.mum.domain.ProductCategory;
import edu.mum.service.CatagoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
public class CatagoryServiceImpl implements CatagoryService {

    @Autowired
    CatagoryRepository catagoryRepository;
    @Override
    @Transactional
    public void addCatagory(ProductCategory productCategory) {
        catagoryRepository.save(productCategory);
    }

    @Override
    @Transactional
    public List<ProductCategory> getCatagory() {
        return catagoryRepository.findAll();
    }

    @Override
    @Transactional
    public ProductCategory findOne(Long id){
        return catagoryRepository.findOne(id);
    }
}
