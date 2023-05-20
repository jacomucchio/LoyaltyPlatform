package it.unicam.cs.ids.loyaltyplatform.level;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIdentityReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LevelLoyaltyPlan;
import jakarta.persistence.*;

@Entity
@Table(name = "level")
public class LevelEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private int requiredPoints;
    private double discountPercentage;
    @ManyToOne
    private LevelEntity nextLevel;

    @ManyToOne
    @JsonIgnore
    private LevelLoyaltyPlan plan;

    public LevelEntity(String name, int requiredPoints, double discountPercentage, LevelLoyaltyPlan plan) {
        this.name = name;
        this.requiredPoints = requiredPoints;
        this.discountPercentage = discountPercentage;
        this.plan=plan;
    }

    public LevelEntity(String name, int requiredPoints, double discountPercentage) {
        this.name = name;
        this.requiredPoints = requiredPoints;
        this.discountPercentage = discountPercentage;
    }

    public LevelEntity() {
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public int getRequiredPoints() {
        return requiredPoints;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setPlan(LevelLoyaltyPlan plan) {
        this.plan = plan;
    }

    public LevelLoyaltyPlan getPlan() {
        return plan;
    }

    public LevelEntity getNextLevel() {
        return nextLevel;
    }

    public void setNextLevel(LevelEntity nextLevel) {
        this.nextLevel = nextLevel;
    }
}
