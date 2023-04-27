package it.unicam.cs.ids.loyaltyplatform.persistence.entity;

import jakarta.persistence.*;

import java.util.Date;
/*
TODO: -finire di implementare l'entità.
      -Finire di implementare la relazione fra tessera e transazione
      -Finire di implementare la relazione fra azienda e transazione
      -rivedere costruttori, getter&setters
 */

@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date date;
    private int amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    private CardEntity customerCard;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    public TransactionEntity() {
    }

    public TransactionEntity(Integer id, Date date, int amount) {
        this.id = id;
        this.date = date;
        this.amount = amount;
    }

    public Integer getId() {
        return id;
    }

    public Date getDate() {
        return date;
    }

    public int getAmount() {
        return amount;
    }

    public CardEntity getCustomerCard() {
        return customerCard;
    }

    public CompanyEntity getCompany() {
        return company;
    }
}
