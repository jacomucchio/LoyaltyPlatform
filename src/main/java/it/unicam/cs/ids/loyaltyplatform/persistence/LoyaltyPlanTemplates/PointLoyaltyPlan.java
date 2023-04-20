package it.unicam.cs.ids.loyaltyplatform.persistence.LoyaltyPlanTemplates;

import it.unicam.cs.ids.loyaltyplatform.persistence.LoyaltyPlanEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("point")
public class PointLoyaltyPlan extends LoyaltyPlanEntity {
    private double conversionRate;

    private int rewardThreshold;
}
