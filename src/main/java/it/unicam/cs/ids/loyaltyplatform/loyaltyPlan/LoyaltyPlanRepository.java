package it.unicam.cs.ids.loyaltyplatform.loyaltyPlan;

import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoyaltyPlanRepository extends JpaRepository<LoyaltyPlanEntity,Integer> {
    List<LoyaltyPlanEntity> findByCompanyId(Integer id);
}
