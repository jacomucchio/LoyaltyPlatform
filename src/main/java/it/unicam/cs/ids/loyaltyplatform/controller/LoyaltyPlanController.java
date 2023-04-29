package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.persistence.entity.LoyaltyPlanEntity;
import it.unicam.cs.ids.loyaltyplatform.service.LoyaltyPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/loyaltyPlans")
public class LoyaltyPlanController {
    private final LoyaltyPlanService loyaltyPlanService;

    @Autowired
    public LoyaltyPlanController(LoyaltyPlanService loyaltyPlanService) {
        this.loyaltyPlanService = loyaltyPlanService;
    }

    @GetMapping("/")
    public List<LoyaltyPlanEntity> findAll() {
        return loyaltyPlanService.findAll();
    }
    @GetMapping("/{type}")
    public List<LoyaltyPlanEntity> findByType(@PathVariable String type) {
        return loyaltyPlanService.findByType(type);
    }

    @PostMapping("/")
    public void save(@RequestBody LoyaltyPlanEntity loyaltyPlan) {
        loyaltyPlanService.save(loyaltyPlan);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        loyaltyPlanService.deleteById(id);
    }
}
