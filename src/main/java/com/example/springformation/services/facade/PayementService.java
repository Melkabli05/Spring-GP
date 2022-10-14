package com.example.springformation.services.facade;

import com.example.springformation.bean.Payement;

import javax.transaction.Transactional;
import java.util.List;


public interface PayementService
{
    Payement findByReference(String reference);
    List<Payement> findByCommandeReference(String reference);




}
