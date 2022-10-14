package com.example.springformation.bean;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Builder
@Data @AllArgsConstructor @NoArgsConstructor
public class Payement {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String reference;
    private double montant;

    @ManyToOne( fetch = FetchType.LAZY)
    private Commande commande;




}




