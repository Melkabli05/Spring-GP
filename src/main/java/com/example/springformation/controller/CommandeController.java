package com.example.springformation.controller;

import com.example.springformation.bean.Commande;
import com.example.springformation.services.facade.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/command")
public class CommandeController {

    @Autowired
    private CommandeService commandeService;

    @PostMapping({"/", ""})
    ResponseEntity<Integer> save(@RequestBody Commande commande){
        return ResponseEntity.ok(commandeService.save(commande));
    }

    @GetMapping({"/", ""})
    ResponseEntity<List<Commande>> findAll(){
        return ResponseEntity.ok(commandeService.findAll());
    }

    @GetMapping("/reference/{reference}")
    ResponseEntity<Commande> findByReference(@PathVariable String reference){
        return ResponseEntity.ok(commandeService.findByReference(reference));
    }

    @DeleteMapping("/reference/{reference}")
    ResponseEntity<Integer> deleteByReference(@PathVariable String reference){
        return ResponseEntity.ok(commandeService.deleteByReference(reference));
    }

    @PutMapping({"/", ""})
    ResponseEntity<Commande> update(@RequestBody Commande commande){
        return ResponseEntity.ok(commandeService.update(commande));
    }




}
