package it.unicam.cs.ids.loyaltyplatform.loyaltyPlan;

import com.fasterxml.jackson.annotation.JsonCreator;
import it.unicam.cs.ids.loyaltyplatform.enrollment.LevelEnrollment;
import it.unicam.cs.ids.loyaltyplatform.enrollment.PointEnrollment;
import it.unicam.cs.ids.loyaltyplatform.level.LevelEntity;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanEntity;
import it.unicam.cs.ids.loyaltyplatform.transaction.TransactionEntity;
import it.unicam.cs.ids.loyaltyplatform.enrollment.EnrollmentEntity;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("level")
public class LevelLoyaltyPlan extends LoyaltyPlanEntity {
    @OneToMany(mappedBy = "plan", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<LevelEntity> levels;
    @OneToOne
    private LevelEntity baseLevel;
    private double conversionRate;
    @JsonCreator
    public LevelLoyaltyPlan(String name, double conversionRate, LevelEntity baseLevel) {
        super(name);
        levels=new ArrayList<>();
        this.conversionRate=conversionRate;
        baseLevel.setPlan(this);
        this.baseLevel=baseLevel;
        levels.add(baseLevel);
    }
    public LevelLoyaltyPlan() {
    }
    @Override
    public EnrollmentEntity applyBenefits(EnrollmentEntity enrollment, TransactionEntity transactionEntity) {
        LevelEnrollment levelEnrollment = (LevelEnrollment) enrollment;
        //aggiunge i punti
        levelEnrollment.setPoints(levelEnrollment.getPoints() +
                (int) (transactionEntity.getAmount() / conversionRate));
        //applica lo sconto
        double cashbackAmount = (transactionEntity.getAmount() * levelEnrollment.
                getCurrentLevel().getDiscountPercentage()) / 100;
        transactionEntity.setAmount(transactionEntity.getAmount()-cashbackAmount);
        return enrollment;
    }

    public List<LevelEntity> getLevels() {
        return levels;
    }

    public void setLevels(List<LevelEntity> levels) {
        this.levels = levels;
    }

    public LevelEntity getBaseLevel() {
        return baseLevel;
    }

    public void setBaseLevel(LevelEntity baseLevel) {
        this.baseLevel = baseLevel;
    }

    public double getConversionRate() {
        return conversionRate;
    }

    public void setConversionRate(double conversionRate) {
        this.conversionRate = conversionRate;
    }
}
