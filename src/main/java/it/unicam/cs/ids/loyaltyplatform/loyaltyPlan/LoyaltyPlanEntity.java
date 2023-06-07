package it.unicam.cs.ids.loyaltyplatform.loyaltyPlan;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonSubTypes;
import com.fasterxml.jackson.annotation.JsonTypeInfo;
import it.unicam.cs.ids.loyaltyplatform.company.CompanyEntity;
import it.unicam.cs.ids.loyaltyplatform.transaction.TransactionEntity;
import it.unicam.cs.ids.loyaltyplatform.enrollment.EnrollmentEntity;
import jakarta.persistence.*;


import java.util.ArrayList;
import java.util.List;


@Entity
@Table (name = "loyalty_plan")
@DiscriminatorColumn(name = "type", discriminatorType = DiscriminatorType.STRING)



@JsonTypeInfo(
        use = JsonTypeInfo.Id.NAME,
        include = JsonTypeInfo.As.PROPERTY,
        property = "type")
@JsonSubTypes({
        @JsonSubTypes.Type(value = PointLoyaltyPlan.class, name = "point"),
        @JsonSubTypes.Type(value = LevelLoyaltyPlan.class, name = "level"),
        @JsonSubTypes.Type(value = CashbackLoyaltyPlan.class, name = "cashback"),
        @JsonSubTypes.Type(value = CoalitionLoyaltyPlan.class, name = "coalition"),
        @JsonSubTypes.Type(value = MembershipLoyaltyPlan.class, name = "membership")
})
public abstract class LoyaltyPlanEntity {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int customerCount;
    @ManyToOne
    @JoinColumn(name = "company_id")
    @JsonBackReference
    private CompanyEntity company;

    @OneToMany(mappedBy="plan")
    @JsonIgnore
    private List<EnrollmentEntity> enrollments = new ArrayList<>();



    public LoyaltyPlanEntity(String name) {
        this.name = name;
        this.customerCount=0;
    }

    public LoyaltyPlanEntity() {
    }

    public List<EnrollmentEntity> getEnrollments() {
        return enrollments;
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

    public void setCompany(CompanyEntity company) {
        this.company = company;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }
    @Transient
    public String getDiscriminatorValue() {
        return this.getClass().getAnnotation(DiscriminatorValue.class).value();
    }

    public abstract EnrollmentEntity applyBenefits(EnrollmentEntity enrollment, TransactionEntity transactionEntity);
}
