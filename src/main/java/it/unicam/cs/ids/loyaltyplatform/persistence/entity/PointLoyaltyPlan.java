package it.unicam.cs.ids.loyaltyplatform.persistence.entity;

import it.unicam.cs.ids.loyaltyplatform.persistence.entity.LoyaltyPlanEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("point")
public class PointLoyaltyPlan extends LoyaltyPlanEntity {
    private double conversionRate;

    private int rewardThreshold;

    public PointLoyaltyPlan(String name, double conversionRate, int rewardThreshold) {
        super(name);
        this.conversionRate = conversionRate;
        this.rewardThreshold = rewardThreshold;
    }

    public PointLoyaltyPlan() {
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }

    public int getRewardThreshold() {
        return rewardThreshold;
    }

    public void setRewardThreshold(int rewardThreshold) {
        this.rewardThreshold = rewardThreshold;
    }
}
