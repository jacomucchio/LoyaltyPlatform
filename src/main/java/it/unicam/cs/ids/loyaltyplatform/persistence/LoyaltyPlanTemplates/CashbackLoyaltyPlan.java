package it.unicam.cs.ids.loyaltyplatform.persistence.LoyaltyPlanTemplates;

import it.unicam.cs.ids.loyaltyplatform.persistence.LoyaltyPlanEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("cashback")
public class CashbackLoyaltyPlan extends LoyaltyPlanEntity {
    private double cashbackPercentage;

    private double maxCashbackAmount;
}
