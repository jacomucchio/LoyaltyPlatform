package it.unicam.cs.ids.loyaltyplatform.persistence.entity;

import it.unicam.cs.ids.loyaltyplatform.persistence.entity.LoyaltyPlanEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("levels")
public class LevelLoyaltyPlan extends LoyaltyPlanEntity {
    private int numberOfLevels;

    private double discountPercentage;

    public LevelLoyaltyPlan(String name, String type, int numberOfLevels, double discountPercentage) {
        super(name, type);
        this.numberOfLevels = numberOfLevels;
        this.discountPercentage = discountPercentage;
    }

    public LevelLoyaltyPlan(int numberOfLevels, double discountPercentage) {
        this.numberOfLevels = numberOfLevels;
        this.discountPercentage = discountPercentage;
    }

    public LevelLoyaltyPlan() {
    }

    public int getNumberOfLevels() {
        return numberOfLevels;
    }

    public void setNumberOfLevels(int numberOfLevels) {
        this.numberOfLevels = numberOfLevels;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }
}
