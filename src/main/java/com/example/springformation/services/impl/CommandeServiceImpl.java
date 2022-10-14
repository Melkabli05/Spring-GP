package com.example.springformation.services.impl;

import com.example.springformation.bean.Commande;
import com.example.springformation.bean.Payement;
import com.example.springformation.bean.Product;
import com.example.springformation.dao.core.CommandeRepository;
import com.example.springformation.dao.core.PayementRepository;
import com.example.springformation.services.facade.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;

@Service
public class CommandeServiceImpl implements CommandeService {

    @Autowired
    CommandeRepository commandeRepository;

    @Autowired
    PayementRepository payementRepository;


    @Override
    public int save(Commande commande) {

        if (commandeRepository.findByReference(commande.getReference()) != null) {
            return -1;
        } else if (commande.getProducts().isEmpty()) {
            return -2;
        } else if (commande.getProducts().stream().distinct().count() != commande.getProducts().size()) {
            return -3;
        } else {
            commande.getProducts().forEach(product -> {
                commande.setPrixTotal(product.getPrix() * product.getQuantite());
            });
            commandeRepository.save(commande);
            return 1;

        }



    }

    @Override
    public Commande findByReference(String reference) {
        Optional<Commande> commande = Optional.ofNullable(commandeRepository.findByReference(reference));
        if (commande.isPresent()) {
            return commande.get();
        } else {
            throw new RuntimeException(String.format("Commande %s not found", reference));
        }
    }

    @Override
    public int deleteByReference(String reference) {
        Optional<Commande> commande = Optional.ofNullable(commandeRepository.findByReference(reference));
        if (commande.isPresent()) {
            commandeRepository.delete(commande.get());
            return 1;
        } else {
            throw new RuntimeException(String.format("Commande %s not found", reference));
        }
    }

    @Override
    public List<Commande> findAll() {
        return commandeRepository.findAll();
    }

    @Override
    public Commande update(Commande commande) {
        Optional<Commande> commande1 = Optional.ofNullable(commandeRepository.findByReference(commande.getReference()));
        if (commande1.isPresent()) {
            commandeRepository.save(commande);
            return commande;
        } else {
            throw new RuntimeException(String.format("Commande %s not found", commande.getReference()));
        }
    }

    @Override
    public List<Product> findProductByCommandeReference(String reference) {
        return Stream.of(commandeRepository.findByReference(reference))
                .filter(c -> c != null)
                .map(c -> c.getProducts())
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Commande %s not found", reference)));
    }

    @Override @Transactional
    public int deleteByReferenceWithPayement(String reference) {
        Optional<Commande> commande = Optional.ofNullable(commandeRepository.findByReference(reference));
        Optional<List<Payement>> payements = Optional.ofNullable(payementRepository.findByCommandeReference(reference));
        payements.ifPresent(payementList -> payementRepository.deleteAll(payementList));
        commande.ifPresent(c -> commandeRepository.delete(c));
        return 1;

    }




}






