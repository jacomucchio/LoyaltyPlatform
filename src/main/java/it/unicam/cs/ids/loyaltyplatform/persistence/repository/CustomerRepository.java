package it.unicam.cs.ids.loyaltyplatform.persistence.repository;

import it.unicam.cs.ids.loyaltyplatform.persistence.entity.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Integer> {
    CustomerEntity getCustomerEntityById(Integer id);
}
