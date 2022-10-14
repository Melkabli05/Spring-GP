package com.example.springformation.dao.core;

import com.example.springformation.bean.Commande;
import com.example.springformation.bean.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    Product findByReference(String reference);

    int deleteByReference(String reference);

}
