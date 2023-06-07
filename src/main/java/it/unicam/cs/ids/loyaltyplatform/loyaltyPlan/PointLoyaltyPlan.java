package it.unicam.cs.ids.loyaltyplatform.loyaltyPlan;

import it.unicam.cs.ids.loyaltyplatform.reward.RewardEntity;
import it.unicam.cs.ids.loyaltyplatform.transaction.TransactionEntity;
import it.unicam.cs.ids.loyaltyplatform.enrollment.EnrollmentEntity;
import it.unicam.cs.ids.loyaltyplatform.enrollment.PointEnrollment;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("point")
public class PointLoyaltyPlan extends LoyaltyPlanEntity {
    private double conversionRate;

    private int rewardThreshold;

    @OneToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE}, mappedBy = "pointLoyaltyPlan", orphanRemoval = true)
    private List<RewardEntity> rewards = new ArrayList<>();

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

    @Override
    public EnrollmentEntity applyBenefits(EnrollmentEntity enrollment, TransactionEntity transactionEntity) {
        PointEnrollment pointEnrollment = (PointEnrollment) enrollment;
        if(pointEnrollment.getPoints()<=rewardThreshold) {
            pointEnrollment.setPoints(pointEnrollment.getPoints() + (int) (transactionEntity.getAmount() / conversionRate));
        }
        return pointEnrollment;
    }

    public List<RewardEntity> getRewards() {
        return rewards;
    }

    public void setRewards(List<RewardEntity> rewards) {
        this.rewards = rewards;
    }

    public void addReward(RewardEntity reward) {
        rewards.add(reward);
        reward.setPointLoyaltyPlan(this);
    }

    public void removeReward(RewardEntity reward) {
        rewards.remove(reward);
        reward.setPointLoyaltyPlan(null);
    }


}
