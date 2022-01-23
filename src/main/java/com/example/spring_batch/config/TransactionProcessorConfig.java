package com.example.spring_batch.config;


import com.example.spring_batch.entities.Compte;
import com.example.spring_batch.entities.Transaction;
import com.example.spring_batch.entities.TransactionDTO;
import com.example.spring_batch.repositories.CompteRepository;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;

@Configuration
public class TransactionProcessorConfig implements ItemProcessor<TransactionDTO, Transaction> {

    private final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");


    private final CompteRepository compteRepository;

    @Autowired
    public TransactionProcessorConfig(CompteRepository compteRepository) {
        this.compteRepository = compteRepository;
    }

    @Override
    public Transaction process(TransactionDTO transactionDTO) throws Exception {
        Transaction transaction = new Transaction();
        transaction.setIdTransaction(transactionDTO.getIdTransaction());
        transaction.setDateTransaction(dateFormat.parse(transactionDTO.getDateTransaction()));

        compteRepository.findById(transactionDTO.getIdCompte()).ifPresentOrElse(compte -> {
            compte.setSolde(compte.getSolde() - transactionDTO.getMontant());
            transaction.setCompte(compte);
        }, () -> {
            Compte compte = new Compte();
            compte.setIdCompte(transactionDTO.getIdCompte());
            compte.setSolde(10000 - transactionDTO.getMontant());
            compteRepository.save(compte);
            transaction.setCompte(compte);
        });

        transaction.setMontant(transactionDTO.getMontant());
        transaction.setDateDebit(Date.from(Instant.now()));
        return transaction;
    }

}
