package it.unicam.cs.ids.loyaltyplatform.level;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LevelRepository extends JpaRepository<LevelEntity,Integer> {
    Optional<LevelEntity> findByIdAndPlan_Id(Integer levelId, Integer planId);
}
