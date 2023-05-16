package it.unicam.cs.ids.loyaltyplatform.transaction;

import it.unicam.cs.ids.loyaltyplatform.transaction.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity,Integer> {
    TransactionEntity getTransactionEntityById(Integer id);
}
