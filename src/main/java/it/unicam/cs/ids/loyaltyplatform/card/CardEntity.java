package it.unicam.cs.ids.loyaltyplatform.card;

import it.unicam.cs.ids.loyaltyplatform.customer.CustomerEntity;
import it.unicam.cs.ids.loyaltyplatform.transaction.TransactionEntity;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "card")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate creationDate;
    @OneToOne(mappedBy = "card")
    private CustomerEntity cardOwner;

    @OneToMany(mappedBy = "customerCard")
    private List<TransactionEntity> transactions;

    public CardEntity() {
    }
    public CardEntity(CustomerEntity cardOwner) {
        this.creationDate = LocalDate.now();
        this.cardOwner = cardOwner;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getCreationDate() {
        return creationDate;
    }

    public CustomerEntity getCardOwner() {
        return cardOwner;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }
}
