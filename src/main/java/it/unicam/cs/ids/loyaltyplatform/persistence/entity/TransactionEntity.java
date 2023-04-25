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

    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity azienda;



}
