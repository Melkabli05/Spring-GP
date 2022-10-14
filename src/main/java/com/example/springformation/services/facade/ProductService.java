package com.example.springformation.services.facade;

import com.example.springformation.bean.Product;

import java.util.List;

public interface ProductService {
    int save(Product product);
    Product findByReference(String reference);
    int deleteByReference(String reference);
    List<Product> findAll();
    boolean update(Product product);

}
