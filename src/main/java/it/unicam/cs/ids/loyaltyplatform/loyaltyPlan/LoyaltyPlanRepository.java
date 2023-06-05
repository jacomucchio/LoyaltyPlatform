package it.unicam.cs.ids.loyaltyplatform.loyaltyPlan;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface LoyaltyPlanRepository extends JpaRepository<LoyaltyPlanEntity,Integer> {
    List<LoyaltyPlanEntity> findByCompanyId(Integer id);
    Optional<LoyaltyPlanEntity> findByIdAndCompany_Id(Integer planId, Integer companyId);
}
