package com.example.spring_batch.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Compte {

    @Id
    private long idCompte;

    private double solde;
    @OneToMany(mappedBy = "compte")
    private List<Transaction> transaction;

}
