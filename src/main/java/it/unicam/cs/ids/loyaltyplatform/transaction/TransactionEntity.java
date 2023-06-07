package it.unicam.cs.ids.loyaltyplatform.transaction;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.cs.ids.loyaltyplatform.card.CardEntity;
import it.unicam.cs.ids.loyaltyplatform.company.CompanyEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "transaction")
public class TransactionEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private LocalDate date;
    private double amount;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "card_id")
    @JsonIgnore
    private CardEntity customerCard;

    @ManyToOne(fetch = FetchType.LAZY)//,  cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REFRESH})
    @JoinColumn(name = "company_id")
    @JsonIgnore
    private CompanyEntity company;

    public TransactionEntity() {
    }

    public TransactionEntity(int amount) {
        this.date = LocalDate.now();
        this.amount = amount;
    }

    public TransactionEntity(double amount,CompanyEntity company, CardEntity card) {
        this.date = LocalDate.now();
        this.amount = amount;
        this.customerCard=card;
        this.company=company;
    }

    public Integer getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public double getAmount() {
        return amount;
    }

    public CardEntity getCustomerCard() {
        return customerCard;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
}
