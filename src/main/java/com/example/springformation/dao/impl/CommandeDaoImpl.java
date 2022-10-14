package com.example.springformation.dao.impl;

import com.example.springformation.VO.CommandeVO;
import com.example.springformation.bean.Commande;
import com.example.springformation.dao.core.CommandeRepository;
import com.example.springformation.dao.facade.CommandeDao;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.EntityManager;
import java.util.List;

public class CommandeDaoImpl implements CommandeDao {

    @Autowired
    private CommandeRepository commandeRepository;

    @Autowired
    private EntityManager entityManager;


    public List<Commande> searchCommande(CommandeVO commandeVO) {
        String reference = commandeVO.getReference();
        Double totaleMin = commandeVO.getMinTotal();
        Double totaleMax = commandeVO.getMaxTotal();

        StringBuilder query = new StringBuilder("SELECT c FROM Commande c WHERE 1=1");
        if (reference != null) {
            query.append(" AND c.reference LIKE '%").append(reference).append("%'");
        } else if (totaleMin != null) {
            query.append(" AND c.prixTotal >= ").append(totaleMin);
        } else if (totaleMax != null) {
            query.append(" AND c.prixTotal <= ").append(totaleMax);
        }

        System.out.println("query = " + query);

        return entityManager.createQuery(query.toString()).getResultList();



    }

    @Override
    public Commande findByReference(String reference) {
        return commandeRepository.findByReference(reference);
    }

    @Override
    public int deleteByReference(String reference) {
        return commandeRepository.deleteByReference(reference);
    }
}
