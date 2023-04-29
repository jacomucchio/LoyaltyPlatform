package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.model.LoyaltyPlan;
import it.unicam.cs.ids.loyaltyplatform.persistence.entity.CashbackLoyaltyPlan;
import it.unicam.cs.ids.loyaltyplatform.persistence.entity.CoalitionLoyaltyPlan;
import it.unicam.cs.ids.loyaltyplatform.persistence.entity.LevelLoyaltyPlan;
import it.unicam.cs.ids.loyaltyplatform.persistence.entity.LoyaltyPlanEntity;
import it.unicam.cs.ids.loyaltyplatform.persistence.repository.LoyaltyPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.NoSuchElementException;

//TODO create LoyaltyPlan non funziona perchè prende come parametro il planType che attualmente non esiste, funzionerebbe
//TODO con un enum

@Service
public class LoyaltyPlanService {
    private final LoyaltyPlanRepository loyaltyPlanRepository;

    @Autowired
    public LoyaltyPlanService(LoyaltyPlanRepository loyaltyPlanRepository) {
        this.loyaltyPlanRepository = loyaltyPlanRepository;
    }

    public List<LoyaltyPlanEntity> findAll() {
        return loyaltyPlanRepository.findAll();
    }

    public LoyaltyPlanEntity findById(Integer id){
        return loyaltyPlanRepository.findById(id)
                 .orElseThrow(() -> new NoSuchElementException("Loyalty plan not found with id: " + id));
    }

    public List<LoyaltyPlanEntity> findByType(String type) {
        return loyaltyPlanRepository.findByType(type);
    }

    public void save(LoyaltyPlanEntity loyaltyPlanEntity) {
        loyaltyPlanRepository.save(loyaltyPlanEntity);
    }

    public void deleteById(Integer id) {
        loyaltyPlanRepository.deleteById(id);
    }

}
