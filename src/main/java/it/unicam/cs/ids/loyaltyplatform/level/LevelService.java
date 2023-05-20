package it.unicam.cs.ids.loyaltyplatform.level;

import it.unicam.cs.ids.loyaltyplatform.customer.CustomerEntity;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LevelLoyaltyPlan;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanEntity;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@Service
public class LevelService {
    private final LevelRepository levelRepository;
    private final LoyaltyPlanService loyaltyPlanService;
    @Autowired

    public LevelService(LevelRepository levelRepository, LoyaltyPlanService loyaltyPlanService)
    {
        this.levelRepository=levelRepository;
        this.loyaltyPlanService=loyaltyPlanService;
    }

    public LevelEntity findById(Integer id)
    {
        return levelRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("level not found with id: "+id));
    }
    /*
    TODO: controlli se piano è a livelli, lanciare eccezione se non lo è.
          Se il cliente aggiunge livello in fondo alla lista va fatto un controllo
          per next level
     */

    public void addLevelToPlan(Integer planId, LevelEntity level) {
        LoyaltyPlanEntity plan = loyaltyPlanService.findById(planId);
        if(plan instanceof LevelLoyaltyPlan)
        {
            LevelLoyaltyPlan levelPlan = (LevelLoyaltyPlan) plan;
            level.setPlan(levelPlan);
            loyaltyPlanService.save(levelPlan);
        }
    }

}
