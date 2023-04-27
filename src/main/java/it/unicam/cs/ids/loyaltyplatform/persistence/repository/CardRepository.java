package it.unicam.cs.ids.loyaltyplatform.persistence.repository;

import it.unicam.cs.ids.loyaltyplatform.persistence.entity.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CardRepository extends JpaRepository<CardEntity,Integer> {
    CardEntity getCardEntityById(Integer id);
}
