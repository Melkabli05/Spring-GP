package com.example.springformation.services.impl;

import com.example.springformation.bean.Payement;
import com.example.springformation.dao.core.PayementRepository;
import com.example.springformation.services.facade.PayementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;
import java.util.stream.Stream;


@Service
public class PayementServiceImpl implements PayementService {

    @Autowired
    private PayementRepository payementRepository;

    public int save(Payement payement) {
        if (payementRepository.findByReference(payement.getReference()) != null) {
            return -1;
        } else if (payement.getMontant() <= 0) {
            return -2;
        } else {
            payementRepository.save(payement);
            return 1;
        }
    }

    @Override
    public Payement findByReference(String reference) {
        return Optional.ofNullable(payementRepository.findByReference(reference))
                .orElseThrow(() -> new RuntimeException(String.format("Payement with reference %s not found", reference)));
    }

    @Override
    public List<Payement> findByCommandeReference(String reference) {
        return Stream.of(payementRepository.findByCommandeReference(reference))
                .filter(payement -> payement != null)
                .findFirst()
                .orElseThrow(() -> new RuntimeException(String.format("Payement with reference %s not found", reference)));
    }



    @Transactional
    public int deleteByReference(String reference) {
        return Optional.ofNullable(payementRepository.findByReference(reference))
                .map(payement -> {
                    payementRepository.delete(payement);
                    return 1;
                }).orElseThrow(() -> new RuntimeException(String.format("Payement with reference %s not found", reference)));

    }

    public List<Payement> findAll() {
        return payementRepository.findAll();
    }




}

