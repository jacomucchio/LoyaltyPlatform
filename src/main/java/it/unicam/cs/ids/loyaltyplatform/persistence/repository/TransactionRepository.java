package it.unicam.cs.ids.loyaltyplatform.persistence.repository;

import it.unicam.cs.ids.loyaltyplatform.persistence.entity.CardEntity;
import it.unicam.cs.ids.loyaltyplatform.persistence.entity.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionRepository extends JpaRepository<TransactionEntity,Integer> {
    TransactionEntity getTransactionEntityById(Integer id);
}
