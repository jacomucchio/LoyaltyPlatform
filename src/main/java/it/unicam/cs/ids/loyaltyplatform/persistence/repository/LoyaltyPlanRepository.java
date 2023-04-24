package it.unicam.cs.ids.loyaltyplatform.persistence.repository;

import it.unicam.cs.ids.loyaltyplatform.persistence.entity.LoyaltyPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyPlanRepository extends JpaRepository<LoyaltyPlanEntity,Integer> {
    LoyaltyPlanEntity getLoyaltyPlanEntityById(Integer id);
}
