package it.unicam.cs.ids.loyaltyplatform.enrollment;

import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.CashbackLoyaltyPlan;
import it.unicam.cs.ids.loyaltyplatform.customer.CustomerEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("cashback")
public class CashbackEnrollment extends EnrollmentEntity{
    public CashbackEnrollment(CustomerEntity customer, CashbackLoyaltyPlan plan) {
        super(customer, plan);
    }

    public CashbackEnrollment() {
    }

}
