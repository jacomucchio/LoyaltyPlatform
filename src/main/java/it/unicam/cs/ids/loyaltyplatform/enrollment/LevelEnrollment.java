package it.unicam.cs.ids.loyaltyplatform.enrollment;

import it.unicam.cs.ids.loyaltyplatform.customer.CustomerEntity;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LevelLoyaltyPlan;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("level")
public class LevelEnrollment extends EnrollmentEntity{
    private int level;

    public LevelEnrollment(CustomerEntity customer, LevelLoyaltyPlan plan) {
        super(customer, plan);
        this.level = 0;
    }

    public LevelEnrollment() {
    }
}
