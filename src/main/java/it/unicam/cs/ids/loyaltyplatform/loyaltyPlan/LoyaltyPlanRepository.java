package it.unicam.cs.ids.loyaltyplatform.loyaltyPlan;

import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LoyaltyPlanRepository extends JpaRepository<LoyaltyPlanEntity,Integer> {
     //List<LoyaltyPlanEntity> findByCompany(Integer company_id);

}
