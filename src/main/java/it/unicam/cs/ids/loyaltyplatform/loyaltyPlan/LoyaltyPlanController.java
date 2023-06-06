package it.unicam.cs.ids.loyaltyplatform.loyaltyPlan;

import it.unicam.cs.ids.loyaltyplatform.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api")
public class LoyaltyPlanController {
    private final LoyaltyPlanService loyaltyPlanService;
    private final CompanyService companyService;

    @Autowired
    public LoyaltyPlanController(LoyaltyPlanService loyaltyPlanService, CompanyService companyService) {
        this.loyaltyPlanService = loyaltyPlanService;
        this.companyService = companyService;
    }


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
    @DeleteMapping("/loyalty-plan/delete/{id}")
    public void deleteById(@PathVariable Integer id) {
        loyaltyPlanService.deleteById(id);
    }

    @GetMapping("/loyalty-plan/{id}")
    public void findById(@PathVariable Integer id) {
        loyaltyPlanService.findById(id);
    }
    @GetMapping("/company/{companyId}/loyalty-plans")
    public ResponseEntity<List<LoyaltyPlanEntity>> findByCompanyId(@PathVariable Integer companyId)
    {
        return ResponseEntity.ok(loyaltyPlanService.findByCompanyId(companyId));
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
