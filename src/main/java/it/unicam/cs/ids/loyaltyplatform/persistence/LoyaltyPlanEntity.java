package it.unicam.cs.ids.loyaltyplatform.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
@Entity
public abstract class LoyaltyPlanEntity {
    @Id
    private Integer id;
    private String name;
}
