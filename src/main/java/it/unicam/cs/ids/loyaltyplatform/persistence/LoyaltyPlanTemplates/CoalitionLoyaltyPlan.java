package it.unicam.cs.ids.loyaltyplatform.persistence.LoyaltyPlanTemplates;

import it.unicam.cs.ids.loyaltyplatform.persistence.LoyaltyPlanEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

import java.util.List;

@Entity
@DiscriminatorValue("coalition")
public class CoalitionLoyaltyPlan extends LoyaltyPlanEntity {
    private List<String> coalitionPartners; // Lista dei partner della coalizione
}
