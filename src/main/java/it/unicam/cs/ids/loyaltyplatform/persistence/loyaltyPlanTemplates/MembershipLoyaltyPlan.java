package it.unicam.cs.ids.loyaltyplatform.persistence.loyaltyPlanTemplates;

import it.unicam.cs.ids.loyaltyplatform.persistence.LoyaltyPlanEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("membership")
public class MembershipLoyaltyPlan extends LoyaltyPlanEntity {
    private double membershipFee;
    private int membershipDuration;
}
