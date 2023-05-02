package it.unicam.cs.ids.loyaltyplatform.persistence.repository;

import it.unicam.cs.ids.loyaltyplatform.persistence.entity.LoyaltyPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LoyaltyPlanRepository extends JpaRepository<LoyaltyPlanEntity,Integer> {
     //List<LoyaltyPlanEntity> findByCompany(Integer company_id);

}
