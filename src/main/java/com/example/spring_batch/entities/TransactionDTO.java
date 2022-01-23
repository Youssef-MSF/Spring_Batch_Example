package com.example.spring_batch.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {

    private long idTransaction;
    private long idCompte;
    private double montant;
    private String dateTransaction;

}
