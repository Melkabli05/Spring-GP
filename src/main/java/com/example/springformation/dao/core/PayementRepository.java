package com.example.springformation.dao.core;



import com.example.springformation.bean.Payement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PayementRepository extends JpaRepository<Payement,Long> {

    Payement findByReference(String reference);
    int deleteByReference(String reference);

    // ref (commande)  ---> paiements

    List<Payement> findByCommandeReference(String reference);
    int deleteByCommandeReference(String reference);



}




