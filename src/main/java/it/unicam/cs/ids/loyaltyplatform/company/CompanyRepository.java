package it.unicam.cs.ids.loyaltyplatform.company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity,Integer> {
    Optional<CompanyEntity> findByEmailAddress(String emailAddress);

}
