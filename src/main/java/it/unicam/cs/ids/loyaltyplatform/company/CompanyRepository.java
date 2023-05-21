package it.unicam.cs.ids.loyaltyplatform.company;

import it.unicam.cs.ids.loyaltyplatform.company.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity,Integer> {
    Optional<CompanyEntity> findByEmailAddress(String emailAddress);

}
