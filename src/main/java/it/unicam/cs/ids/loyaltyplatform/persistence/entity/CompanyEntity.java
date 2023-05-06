package it.unicam.cs.ids.loyaltyplatform.persistence.entity;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.Table;


@Entity
@Table(name = "company")
public class CompanyEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String emailAddress;
    @OneToMany (mappedBy = "company", cascade =  CascadeType.ALL)
    private List<LoyaltyPlanEntity> loyaltyPlans=new ArrayList<>();

    @OneToMany
    private List<TransactionEntity> transactions;

    public CompanyEntity( String name, String emailAddress) {
        this.name = name;
        this.emailAddress = emailAddress;
    }

    public CompanyEntity() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }
}
