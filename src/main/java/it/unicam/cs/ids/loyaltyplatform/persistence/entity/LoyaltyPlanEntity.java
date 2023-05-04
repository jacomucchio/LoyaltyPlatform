package it.unicam.cs.ids.loyaltyplatform.persistence.entity;

import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.*;

import java.util.List;

/*
TODO: -private String type ci va o è una ripetizione con DiscriminatorColumn?
      -type enum o string?
      -costruttore deve avere azienda?

 */
@Entity
@Table (name = "loyalty_plan")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)



@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PointLoyaltyPlan.class, name = "point"),
        @JsonSubTypes.Type(value = LevelLoyaltyPlan.class, name = "level")
})
public abstract class LoyaltyPlanEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int customerCount;
    @ManyToOne
    @JoinColumn(name = "company_id")
    private CompanyEntity company;

    @OneToMany(mappedBy = "loyaltyPlan", cascade =CascadeType.ALL)
    private List<PlanEnrollmentEntity> enrollments;



    public LoyaltyPlanEntity(String name) {
        this.name = name;
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
