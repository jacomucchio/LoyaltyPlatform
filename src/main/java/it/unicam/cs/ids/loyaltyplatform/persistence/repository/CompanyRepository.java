package it.unicam.cs.ids.loyaltyplatform.persistence.repository;

import it.unicam.cs.ids.loyaltyplatform.persistence.entity.CompanyEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<CompanyEntity,Integer> {

}
