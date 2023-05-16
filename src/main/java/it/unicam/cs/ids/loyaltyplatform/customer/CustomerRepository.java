package it.unicam.cs.ids.loyaltyplatform.customer;

import it.unicam.cs.ids.loyaltyplatform.customer.CustomerEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Integer> {
    CustomerEntity getCustomerEntityById(Integer id);
}
