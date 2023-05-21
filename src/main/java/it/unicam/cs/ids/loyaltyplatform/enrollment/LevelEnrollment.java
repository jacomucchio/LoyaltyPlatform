package it.unicam.cs.ids.loyaltyplatform.enrollment;

import com.fasterxml.jackson.annotation.JsonCreator;
import it.unicam.cs.ids.loyaltyplatform.customer.CustomerEntity;
import it.unicam.cs.ids.loyaltyplatform.level.LevelEntity;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LevelLoyaltyPlan;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToOne;

@Entity
@DiscriminatorValue("level")
public class LevelEnrollment extends EnrollmentEntity{
    @OneToOne
    private LevelEntity currentLevel;
    private int points;

    @JsonCreator
    public LevelEnrollment(CustomerEntity customer, LevelLoyaltyPlan plan) {
        super(customer, plan);
        currentLevel=plan.getBaseLevel();
    }

    public LevelEnrollment() {
    }

    public LevelEntity getCurrentLevel() {
        return currentLevel;
    }

    public void setCurrentLevel(LevelEntity currentLevel) {
        this.currentLevel = currentLevel;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }
}
