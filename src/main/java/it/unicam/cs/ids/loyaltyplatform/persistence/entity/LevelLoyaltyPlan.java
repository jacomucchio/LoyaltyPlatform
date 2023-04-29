package it.unicam.cs.ids.loyaltyplatform.persistence.entity;

import it.unicam.cs.ids.loyaltyplatform.persistence.entity.LoyaltyPlanEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("levels")
public class LevelLoyaltyPlan extends LoyaltyPlanEntity {
    private int numberOfLevels;

    private double discountPercentage;
}
