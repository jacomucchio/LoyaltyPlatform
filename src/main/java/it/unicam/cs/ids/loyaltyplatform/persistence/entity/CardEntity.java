package it.unicam.cs.ids.loyaltyplatform.persistence.entity;

import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

/*
TODO: -finire di implementare l'entità.
      -Finire di implementare la relazione fra carta e proprietario
      -rivedere costruttori, getter&setters
 */
@Entity
@Table(name = "card")
public class CardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private LocalDate creationDate;
    @OneToOne(mappedBy = "card")
    private CustomerEntity cardOwner;

    @OneToMany(mappedBy = "customerCard")
    private List<TransactionEntity> transactions;

    public CardEntity() {
    }
    public CardEntity(LocalDate creationDate, CustomerEntity cardOwner) {
        this.creationDate = creationDate;
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


}
