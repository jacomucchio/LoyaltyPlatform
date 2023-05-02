package it.unicam.cs.ids.loyaltyplatform.persistence.entity;

import jakarta.persistence.*;

/*
TODO: -private String type ci va o è una ripetizione con DiscriminatorColumn?
      -type enum o string?
      -rivedere costruttori, getter&setters
 */
@Entity
@Table (name = "loyalty_plan")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)
public abstract class LoyaltyPlanEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    @Column(name = "type", insertable = false, updatable = false)
    private String type;
    private int customerCount;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    public LoyaltyPlanEntity(String name, String type) {
        this.name = name;
        this.type =type;
        this.customerCount=0;
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
