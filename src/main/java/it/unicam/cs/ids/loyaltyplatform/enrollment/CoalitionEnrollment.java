package it.unicam.cs.ids.loyaltyplatform.enrollment;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.CoalitionLoyaltyPlan;
import it.unicam.cs.ids.loyaltyplatform.customer.CustomerEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("coalition")
public class CoalitionEnrollment extends EnrollmentEntity{
    public CoalitionEnrollment(CustomerEntity customer, CoalitionLoyaltyPlan plan) {
        super(customer, plan);
    }

    public CoalitionEnrollment() {
    }
}
