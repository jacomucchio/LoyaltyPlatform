package it.unicam.cs.ids.loyaltyplatform.card;

import it.unicam.cs.ids.loyaltyplatform.card.CardEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CardRepository extends JpaRepository<CardEntity,Integer> {
}
