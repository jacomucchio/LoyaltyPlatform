package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.model.LoyaltyPlan;
import it.unicam.cs.ids.loyaltyplatform.persistence.entity.*;
import it.unicam.cs.ids.loyaltyplatform.persistence.repository.CompanyRepository;
import it.unicam.cs.ids.loyaltyplatform.persistence.repository.LoyaltyPlanRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LoyaltyPlanService {
    private final LoyaltyPlanRepository loyaltyPlanRepository;
    private final CompanyRepository companyRepository;

    @Autowired
    public LoyaltyPlanService(LoyaltyPlanRepository loyaltyPlanRepository, CompanyRepository companyRepository) {
        this.loyaltyPlanRepository = loyaltyPlanRepository;
        this.companyRepository = companyRepository;
    }

    public LoyaltyPlanEntity createLoyaltyPlan(Integer companyId, LoyaltyPlanEntity loyaltyPlan) {
        CompanyEntity company = companyRepository.findById(companyId).orElseThrow(() ->
                new IllegalArgumentException("Company not found with id " + companyId));
        loyaltyPlan.setCompany(company);
        return loyaltyPlanRepository.save(loyaltyPlan);
    }

    public List<LoyaltyPlanEntity> findAll() {
        return loyaltyPlanRepository.findAll();
    }

    public LoyaltyPlanEntity findById(Integer id){
        return loyaltyPlanRepository.findById(id)
                 .orElseThrow(() -> new NoSuchElementException("Loyalty plan not found with id: " + id));
    }

    public void save(LoyaltyPlanEntity loyaltyPlanEntity) {
        loyaltyPlanRepository.save(loyaltyPlanEntity);
    }

    public void deleteById(Integer id) {
        loyaltyPlanRepository.deleteById(id);
    }

    public ResponseEntity<List<CustomerEntity>> getCustomerListByPlanId(Integer planId) {
        LoyaltyPlanEntity loyaltyPlan = loyaltyPlanRepository.findById(planId)
                .orElseThrow(() -> new NoSuchElementException("Loyalty plan not found with id: " + planId));
        List<CustomerEntity> customers = loyaltyPlan.getEnrolledCustomers();
        return ResponseEntity.ok().body(customers);
    }

}
