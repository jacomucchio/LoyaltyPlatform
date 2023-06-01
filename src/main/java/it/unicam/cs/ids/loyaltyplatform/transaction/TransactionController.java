package it.unicam.cs.ids.loyaltyplatform.transaction;
import it.unicam.cs.ids.loyaltyplatform.card.CardEntity;
import it.unicam.cs.ids.loyaltyplatform.company.CompanyEntity;
import it.unicam.cs.ids.loyaltyplatform.card.CardService;
import it.unicam.cs.ids.loyaltyplatform.company.CompanyService;
import it.unicam.cs.ids.loyaltyplatform.transaction.TransactionEntity;
import it.unicam.cs.ids.loyaltyplatform.transaction.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/api")
public class TransactionController {
    private final TransactionService transactionService;
    private final CompanyService companyService;
    private final CardService cardService;

    public TransactionController(TransactionService transactionService, CompanyService companyService,
                                 CardService cardService) {
        this.transactionService = transactionService;
        this.companyService=companyService;
        this.cardService=cardService;
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

    @PostMapping("/transaction/card/{cardId}/validate/company/{companyId}/amount")
    public ResponseEntity<TransactionEntity> validateTransaction(@PathVariable Integer companyId, @PathVariable Integer cardId,
                                              @RequestParam double amount)
    {
        CompanyEntity company = companyService.getCompanyById(companyId);
        CardEntity card = cardService.getCardById(cardId);
        return ResponseEntity.ok(transactionService.validateTransaction(company, card, amount));
    }
}
