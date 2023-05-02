package it.unicam.cs.ids.loyaltyplatform.persistence.entity;

import it.unicam.cs.ids.loyaltyplatform.persistence.entity.LoyaltyPlanEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
@DiscriminatorValue("coalition")
public class CoalitionLoyaltyPlan extends LoyaltyPlanEntity {
    private List<String> coalitionPartners; // Lista dei partner della coalizione

    public CoalitionLoyaltyPlan(String name, List<String> coalitionPartners) {
        super(name);
        this.coalitionPartners = coalitionPartners;
    }

    public CoalitionLoyaltyPlan(List<String> coalitionPartners) {
        this.coalitionPartners = coalitionPartners;
    }

    public CoalitionLoyaltyPlan() {
    }

    public void setCoalitionPartners(List<String> coalitionPartners) {
        this.coalitionPartners = coalitionPartners;
    }
}
