package it.unicam.cs.ids.loyaltyplatform.transaction;

import it.unicam.cs.ids.loyaltyplatform.company.CompanyEntity;
import it.unicam.cs.ids.loyaltyplatform.transaction.TransactionEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface TransactionRepository extends JpaRepository<TransactionEntity,Integer> {
    TransactionEntity getTransactionEntityById(Integer id);

    Collection<? extends TransactionEntity> findByCompany(CompanyEntity company);
}
