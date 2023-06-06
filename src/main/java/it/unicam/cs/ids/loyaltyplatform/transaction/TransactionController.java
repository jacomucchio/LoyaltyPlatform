package it.unicam.cs.ids.loyaltyplatform.transaction;
import it.unicam.cs.ids.loyaltyplatform.card.CardEntity;
import it.unicam.cs.ids.loyaltyplatform.company.CompanyEntity;
import it.unicam.cs.ids.loyaltyplatform.card.CardService;
import it.unicam.cs.ids.loyaltyplatform.company.CompanyService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

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
    @GetMapping("/transactions")
    public List<TransactionEntity> findAll(){
        return transactionService.findAll();
    }
    @GetMapping("/transaction/{id}")
    public TransactionEntity findById(@PathVariable Integer id){
        return transactionService.findById(id);
    }

    @PostMapping("/transaction/save")
    public void save(TransactionEntity transactionEntity)
    {
        transactionService.save(transactionEntity);
    }
    @DeleteMapping("/transaction/delete/{id}")
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
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
