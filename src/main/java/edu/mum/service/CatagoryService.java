package edu.mum.service;


import edu.mum.domain.ProductCategory;

public interface CatagoryService {

    public void addCatagory(ProductCategory productCategory);
    public Iterable<ProductCategory> getCatagory();
    public ProductCategory findOne(Long id);
}
