package it.unicam.cs.ids.loyaltyplatform.controller;
import it.unicam.cs.ids.loyaltyplatform.persistence.entity.TransactionEntity;
import it.unicam.cs.ids.loyaltyplatform.service.TransactionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/Transaction")
public class TransactionController {
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }
    @GetMapping("/")
    public List<TransactionEntity> findAll(){
        return transactionService.findAll();
    }
    @GetMapping("/{id}")
    public TransactionEntity findById(@PathVariable Integer id){
        return transactionService.findById(id);
    }

    @PostMapping("/")
    public void save(TransactionEntity transactionEntity)
    {
        transactionService.save(transactionEntity);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id)
    {
        transactionService.deleteById(id);
    }
}
