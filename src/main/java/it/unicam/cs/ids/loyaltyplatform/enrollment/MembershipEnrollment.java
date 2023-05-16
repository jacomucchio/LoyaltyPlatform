package it.unicam.cs.ids.loyaltyplatform.enrollment;

import it.unicam.cs.ids.loyaltyplatform.customer.CustomerEntity;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.MembershipLoyaltyPlan;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("membership")
public class MembershipEnrollment extends EnrollmentEntity{
    public MembershipEnrollment(CustomerEntity customer, MembershipLoyaltyPlan plan) {
        super(customer, plan);
    }
    public MembershipEnrollment() {
    }
}
