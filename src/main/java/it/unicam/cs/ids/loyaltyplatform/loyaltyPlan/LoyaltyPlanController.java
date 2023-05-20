package it.unicam.cs.ids.loyaltyplatform.loyaltyPlan;

import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanEntity;
import it.unicam.cs.ids.loyaltyplatform.company.CompanyService;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanService;
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

    @GetMapping("/loyalty-plan")
    public List<LoyaltyPlanEntity> findAll() {
        return loyaltyPlanService.findAll();
    }

    @PostMapping("/loyalty-plan/{companyId}")
    public void save(@RequestBody LoyaltyPlanEntity loyaltyPlan, @PathVariable Integer companyId) {
        loyaltyPlan.setCompany(companyService.getCompanyById(companyId));
        loyaltyPlanService.save(loyaltyPlan);
    }
    @PostMapping("/company/{companyId}/create-plan")
    public ResponseEntity<LoyaltyPlanEntity> createPlan(@PathVariable Integer companyId,
                                                        @RequestBody LoyaltyPlanEntity loyaltyPlan) {
        LoyaltyPlanEntity newPlan = loyaltyPlanService.createLoyaltyPlan(companyId, loyaltyPlan);
        return ResponseEntity.ok(newPlan);
    }

    @DeleteMapping("/loyalty-plan{id}")
    public void deleteById(@PathVariable Integer id) {
        loyaltyPlanService.deleteById(id);
    }

    @GetMapping("/loyalty-plan{id}")
    public void findById(@PathVariable Integer id) {
        loyaltyPlanService.findById(id);
    }
    @GetMapping("/company/{companyId}/loyalty-plans")
    public ResponseEntity<List<LoyaltyPlanEntity>> findByCompanyId(@PathVariable Integer companyId)
    {
        return ResponseEntity.ok(loyaltyPlanService.findByCompanyId(companyId));
    }
}
