package it.unicam.cs.ids.loyaltyplatform.persistence.LoyaltyPlanTemplates;

import it.unicam.cs.ids.loyaltyplatform.persistence.LoyaltyPlanEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("levels")
public class LevelLoyaltyPlan extends LoyaltyPlanEntity {
    private int numberOfLevels;

    private double discountPercentage;
}
