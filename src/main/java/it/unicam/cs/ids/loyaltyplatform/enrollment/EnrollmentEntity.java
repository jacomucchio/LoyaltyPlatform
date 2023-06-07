package it.unicam.cs.ids.loyaltyplatform.enrollment;

import it.unicam.cs.ids.loyaltyplatform.customer.CustomerEntity;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "enrollment")
@DiscriminatorColumn(name = "plan_type", discriminatorType = DiscriminatorType.STRING)
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class EnrollmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "customer_id")
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private CustomerEntity customer;

    @ManyToOne //(cascade = CascadeType.ALL)
    @JoinColumn(name = "plan_id")
    //@OnDelete(action = OnDeleteAction.CASCADE)
    private LoyaltyPlanEntity plan;

    private LocalDate date;

    public EnrollmentEntity(CustomerEntity customer, LoyaltyPlanEntity plan) {
        this.customer = customer;
        this.plan = plan;
        this.date=LocalDate.now();
    }

    public EnrollmentEntity() {
    }

    public Integer getId() {
        return id;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public LoyaltyPlanEntity getPlan() {
        return plan;
    }

    public LocalDate getDate() {
        return date;
    }
}
