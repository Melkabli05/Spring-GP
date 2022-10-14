package com.example.springformation.dao.facade;

import com.example.springformation.bean.Commande;

public interface CommandeDao {

    Commande findByReference(String reference);
    int deleteByReference(String reference);

}
