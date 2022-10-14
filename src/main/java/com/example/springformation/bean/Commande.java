package com.example.springformation.bean;




import lombok.*;

import javax.persistence.*;
import java.util.List;


@Entity @Builder
@Data @AllArgsConstructor @NoArgsConstructor
public class Commande {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String reference;
    private double prixTotal;
    private double totalPaiement;
    private int mois;
    private int annee;
    @OneToMany( fetch = FetchType.LAZY)
    private List<Product> products;
}



