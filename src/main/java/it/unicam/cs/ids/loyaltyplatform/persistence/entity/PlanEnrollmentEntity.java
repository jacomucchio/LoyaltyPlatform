package it.unicam.cs.ids.loyaltyplatform.persistence.entity;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table(name = "plan_enrollment")
public class PlanEnrollmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "customer_id",referencedColumnName = "id")
    private CustomerEntity customer;

    @ManyToOne
    @JoinColumn(name = "loyalty_plan_id",referencedColumnName = "id")
    private LoyaltyPlanEntity loyaltyPlan;
    private LocalDate date;

    public PlanEnrollmentEntity(CustomerEntity customer, LoyaltyPlanEntity loyaltyPlan) {
        this.customer = customer;
        this.loyaltyPlan = loyaltyPlan;
        this.date= LocalDate.now();
    }

    public PlanEnrollmentEntity() {

    }

    public Integer getId() {
        return id;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public LoyaltyPlanEntity getLoyaltyPlan() {
        return loyaltyPlan;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public void setLoyaltyPlan(LoyaltyPlanEntity loyaltyPlan) {
        this.loyaltyPlan = loyaltyPlan;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }
}
