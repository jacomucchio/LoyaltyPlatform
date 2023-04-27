package it.unicam.cs.ids.loyaltyplatform.persistence.entity;

import jakarta.persistence.*;

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
    private Date creationDate;
    @OneToOne(mappedBy = "card")
    private CustomerEntity cardOwner;

    @OneToMany(mappedBy = "customerCard")
    private List<TransactionEntity> transactions;

    public CardEntity() {
    }
    public CardEntity(Date creationDate, CustomerEntity cardOwner) {
        this.creationDate = creationDate;
        this.cardOwner = cardOwner;
    }

    public Integer getId() {
        return id;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public CustomerEntity getCardOwner() {
        return cardOwner;
    }


}
