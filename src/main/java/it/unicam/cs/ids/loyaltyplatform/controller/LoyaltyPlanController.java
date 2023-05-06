package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.persistence.entity.CustomerEntity;
import it.unicam.cs.ids.loyaltyplatform.persistence.entity.LoyaltyPlanEntity;
import it.unicam.cs.ids.loyaltyplatform.service.CompanyService;
import it.unicam.cs.ids.loyaltyplatform.service.LoyaltyPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//TODO ho cambiato requestmapping da /loyaltyplans a /api, non so se va bene
@RestController
@RequestMapping("/api")
public class LoyaltyPlanController {
    private final LoyaltyPlanService loyaltyPlanService;

    @Autowired
    public LoyaltyPlanController(LoyaltyPlanService loyaltyPlanService) {
        this.loyaltyPlanService = loyaltyPlanService;
    }

    @Autowired
    private CompanyService companyService;

    @GetMapping("/")
    public List<LoyaltyPlanEntity> findAll() {
        return loyaltyPlanService.findAll();
    }

    @PostMapping("/{companyId}")
    public void save(@RequestBody LoyaltyPlanEntity loyaltyPlan, @PathVariable Integer companyId) {
        loyaltyPlan.setCompany(companyService.getCompanyById(companyId));
        loyaltyPlanService.save(loyaltyPlan);
    }
    //TODO: controllare se create Plan funziona, dovrebbe sostituire save
    @PostMapping("/companies/{companyId}/piani-fedelta")
    public ResponseEntity<LoyaltyPlanEntity> createPlan(@PathVariable Integer companyId,
                                                        @RequestBody LoyaltyPlanEntity loyaltyPlan) {
        LoyaltyPlanEntity newPlan = loyaltyPlanService.createLoyaltyPlan(companyId, loyaltyPlan);
        return ResponseEntity.ok(newPlan);
    }

    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id) {
        loyaltyPlanService.deleteById(id);
    }

    @GetMapping("/{id}")
    public void findById(@PathVariable Integer id) {
        loyaltyPlanService.findById(id);
    }

    @GetMapping("/loyaltyPlan/{planId}/customers")
    public ResponseEntity<List<CustomerEntity>> getCustomerListByPlanId(@PathVariable Integer planId) {
        return loyaltyPlanService.getCustomerListByPlanId(planId);
    }
}
