package it.unicam.cs.ids.loyaltyplatform.company;

import it.unicam.cs.ids.loyaltyplatform.company.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity,Integer> {

}
