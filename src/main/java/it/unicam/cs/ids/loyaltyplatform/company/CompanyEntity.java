package it.unicam.cs.ids.loyaltyplatform.company;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanEntity;
import it.unicam.cs.ids.loyaltyplatform.transaction.TransactionEntity;
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
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String password;
    @OneToMany (mappedBy = "company", cascade =  CascadeType.ALL)
    @JsonManagedReference
    private List<LoyaltyPlanEntity> loyaltyPlans=new ArrayList<>();

    @OneToMany
    private List<TransactionEntity> transactions;

    public CompanyEntity( String name, String emailAddress, String password) {
        this.name = name;
        this.emailAddress = emailAddress;
        this.password=password;
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

    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public void setName(String name) {
        this.name = name;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public List<LoyaltyPlanEntity> getLoyaltyPlans() {
        return loyaltyPlans;
    }

    public void setLoyaltyPlans(List<LoyaltyPlanEntity> loyaltyPlans) {
        this.loyaltyPlans = loyaltyPlans;
    }

    public List<TransactionEntity> getTransactions() {
        return transactions;
    }

    public void setTransactions(List<TransactionEntity> transactions) {
        this.transactions = transactions;
    }
}
