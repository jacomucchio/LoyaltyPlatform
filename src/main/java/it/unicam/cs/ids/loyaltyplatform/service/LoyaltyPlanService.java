package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.persistence.entity.LoyaltyPlanEntity;
import it.unicam.cs.ids.loyaltyplatform.persistence.repository.LoyaltyPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class LoyaltyPlanService {
    @Autowired
    private LoyaltyPlanRepository loyaltyPlanRepository;

    public LoyaltyPlanEntity getById(Integer id) {
        return this.loyaltyPlanRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

}
