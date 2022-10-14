package com.example.springformation;

import com.example.springformation.bean.Commande;
import com.example.springformation.bean.Product;
import com.example.springformation.dao.core.CommandeRepository;
import com.example.springformation.dao.core.PayementRepository;
import com.example.springformation.dao.core.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.stream.Collectors;
import java.util.stream.Stream;

@SpringBootApplication
public class SpringFormationApplication implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(SpringFormationApplication.class, args);
    }



    @Autowired
    private ProductRepository productRepository;
    @Autowired CommandeRepository commandeRepository;

    @Autowired
    private PayementRepository payementRepository;


    @Override
    public void run(String... args) throws Exception {

    for (int i = 0; i < 10; i++) {

        Product product = Product.builder()
                .reference("product" + i)
                .prix(1000)
                .quantite(10)
                .build();
        productRepository.save(product);
        Commande commande = Commande.builder()
                .reference("commande" + i)
                .products(Stream.of(product).collect(Collectors.toList()))
                .prixTotal(1000)
                .totalPaiement(0)
                .build();
        commandeRepository.save(commande);


    }

    }
}


