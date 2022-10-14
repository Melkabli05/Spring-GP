package com.example.springformation.services.impl;

import com.example.springformation.bean.Product;
import com.example.springformation.dao.core.ProductRepository;
import com.example.springformation.services.facade.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Override
    public int save(Product product) {
        return Stream.of(product)
                .filter(p -> productRepository.findByReference(p.getReference()) == null)
                .map(p -> productRepository.save(p))
                .mapToInt(p -> 1)
                .sum();
    }


    @Override
    public Product findByReference(String reference) {
        return Optional.ofNullable(productRepository.findByReference(reference))
                .orElseThrow(() -> new RuntimeException(String.format("Product %s not found", reference)));
    }

    @Override
    public int deleteByReference(String reference) {
        return Optional.ofNullable(productRepository.findByReference(reference))
                .map(product -> {
                    productRepository.delete(product);
                    return 1;
                }).orElseThrow(() -> new RuntimeException(String.format("Product %s not found", reference)));
    }

    @Override
    public List<Product> findAll() {
        return productRepository.findAll();
    }

    @Override
    public boolean update(Product product) {
       return Optional.ofNullable(productRepository.findByReference(product.getReference()))
                .map(product1 -> {
                    productRepository.save(product);
                    return true;
                }).orElseThrow(() -> new RuntimeException(String.format("Product %s not found", product.getReference())));

    }

}
