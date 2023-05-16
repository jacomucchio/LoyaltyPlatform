package it.unicam.cs.ids.loyaltyplatform.loyaltyPlan;

import it.unicam.cs.ids.loyaltyplatform.transaction.TransactionEntity;
import it.unicam.cs.ids.loyaltyplatform.enrollment.EnrollmentEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("levels")
public class LevelLoyaltyPlan extends LoyaltyPlanEntity {
    private int numberOfLevels;

    private int points;

    public LevelLoyaltyPlan(String name, int numberOfLevels, int points) {
        super(name);
        this.numberOfLevels = numberOfLevels;
        this.points = points;
    }

    public LevelLoyaltyPlan(int numberOfLevels, int points) {
        this.numberOfLevels = numberOfLevels;
        this.points = points;
    }

    public LevelLoyaltyPlan() {
    }

    @Override
    public EnrollmentEntity applyBenefits(EnrollmentEntity enrollment, TransactionEntity transactionEntity) {
        return null;
    }

    public int getNumberOfLevels() {
        return numberOfLevels;
    }

    public void setNumberOfLevels(int numberOfLevels) {
        this.numberOfLevels = numberOfLevels;
    }

}
