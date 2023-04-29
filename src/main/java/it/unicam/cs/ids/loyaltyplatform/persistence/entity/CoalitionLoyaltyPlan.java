package it.unicam.cs.ids.loyaltyplatform.persistence.entity;

import it.unicam.cs.ids.loyaltyplatform.persistence.entity.LoyaltyPlanEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
@DiscriminatorValue("coalition")
public class CoalitionLoyaltyPlan extends LoyaltyPlanEntity {
    private List<String> coalitionPartners; // Lista dei partner della coalizione
}
