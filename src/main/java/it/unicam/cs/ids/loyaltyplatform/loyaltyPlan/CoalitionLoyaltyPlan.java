package it.unicam.cs.ids.loyaltyplatform.loyaltyPlan;

import it.unicam.cs.ids.loyaltyplatform.transaction.TransactionEntity;
import it.unicam.cs.ids.loyaltyplatform.enrollment.EnrollmentEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
@DiscriminatorValue("coalition")
public class CoalitionLoyaltyPlan extends LoyaltyPlanEntity {
    private List<String> coalitionPartners; // Lista dei partner della coalizione

    public CoalitionLoyaltyPlan(String name, List<String> coalitionPartners) {
        super(name);
        this.coalitionPartners = coalitionPartners;
    }

    public CoalitionLoyaltyPlan(List<String> coalitionPartners) {
        this.coalitionPartners = coalitionPartners;
    }

    public CoalitionLoyaltyPlan() {
    }

    @Override
    public EnrollmentEntity applyBenefits(EnrollmentEntity enrollment, TransactionEntity transactionEntity) {
        return null;
    }

    public void setCoalitionPartners(List<String> coalitionPartners) {
        this.coalitionPartners = coalitionPartners;
    }
}
