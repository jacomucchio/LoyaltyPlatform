package it.unicam.cs.ids.loyaltyplatform.persistence.entity;

import it.unicam.cs.ids.loyaltyplatform.persistence.entity.LoyaltyPlanEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("point")
public class PointLoyaltyPlan extends LoyaltyPlanEntity {
    private double conversionRate;

    private int rewardThreshold;

    public PointLoyaltyPlan(String name, String type, double conversionRate, int rewardThreshold) {
        super(name, type);
        this.conversionRate = conversionRate;
        this.rewardThreshold = rewardThreshold;
    }

    public PointLoyaltyPlan(double conversionRate, int rewardThreshold) {
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
