package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.persistence.entity.TransactionEntity;
import it.unicam.cs.ids.loyaltyplatform.persistence.repository.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;

    @Autowired
    public TransactionService(TransactionRepository transactionRepository) {
        this.transactionRepository = transactionRepository;
    }
    public List<TransactionEntity> findAll(){return transactionRepository.findAll();}

    public TransactionEntity findById(Integer id){
        return transactionRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Transaction not found with id: "+id));
    }

    public void save(TransactionEntity transactionEntity){transactionRepository.save(transactionEntity);}
    public void deleteById(Integer id){transactionRepository.deleteById(id);}

    }


