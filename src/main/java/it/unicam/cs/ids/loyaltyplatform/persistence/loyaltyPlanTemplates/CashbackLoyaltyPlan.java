package it.unicam.cs.ids.loyaltyplatform.persistence.loyaltyPlanTemplates;

import it.unicam.cs.ids.loyaltyplatform.persistence.entity.LoyaltyPlanEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("cashback")
public class CashbackLoyaltyPlan extends LoyaltyPlanEntity {
    private double cashbackPercentage;

    private double maxCashbackAmount;
}
