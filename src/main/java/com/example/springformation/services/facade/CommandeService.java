package com.example.springformation.services.facade;



import com.example.springformation.bean.Commande;
import com.example.springformation.bean.Product;


import java.util.List;

public interface CommandeService {
    int save(Commande commande);
    Commande findByReference(String reference);
    int deleteByReference(String reference);
    List<Commande> findAll();

    Commande update(Commande commande);

    List<Product> findProductByCommandeReference(String reference);

    int deleteByReferenceWithPayement(String reference);




}





