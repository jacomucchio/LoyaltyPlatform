package it.unicam.cs.ids.loyaltyplatform.enrollment;

import it.unicam.cs.ids.loyaltyplatform.customer.CustomerEntity;
import it.unicam.cs.ids.loyaltyplatform.level.LevelEntity;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface EnrollmentRepository extends JpaRepository<EnrollmentEntity,Integer> {
    Optional<EnrollmentEntity> findByPlanAndCustomer(LoyaltyPlanEntity plan, CustomerEntity customer);
    @Query("SELECT e FROM LevelEnrollment e WHERE e.currentLevel = :currentLevel")
    List<LevelEnrollment> findByCurrentLevel(@Param("currentLevel") LevelEntity currentLevel);


    List<? extends EnrollmentEntity> findByPlan(LoyaltyPlanEntity plan);
}
