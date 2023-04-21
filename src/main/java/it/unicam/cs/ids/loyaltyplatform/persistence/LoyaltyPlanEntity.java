package it.unicam.cs.ids.loyaltyplatform.persistence;

import jakarta.persistence.*;

@Entity
@Table (name = "loyalty_plan")
public abstract class LoyaltyPlanEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int customerCount;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    public LoyaltyPlanEntity(String name) {
        this.name = name;
    }

    public LoyaltyPlanEntity() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public CompanyEntity getCompany() {
        return company;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }
}
