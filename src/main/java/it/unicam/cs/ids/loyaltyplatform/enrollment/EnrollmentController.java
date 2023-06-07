package it.unicam.cs.ids.loyaltyplatform.enrollment;

import it.unicam.cs.ids.loyaltyplatform.customer.CustomerEntity;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanEntity;
import it.unicam.cs.ids.loyaltyplatform.customer.CustomerService;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/api")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    private final CustomerService customerService;
    private final LoyaltyPlanService planService;
    @Autowired
    public EnrollmentController(@Lazy EnrollmentService enrollmentService, CustomerService customerService,
                                LoyaltyPlanService planService) {
        this.enrollmentService = enrollmentService;
        this.customerService=customerService;
        this.planService=planService;
    }

    @PostMapping("/customer/{customerId}/enroll-to-plan/{planId}")
    public ResponseEntity<EnrollmentEntity> enroll(@PathVariable Integer customerId, @PathVariable Integer planId) {
        CustomerEntity customer = customerService.findById(customerId);
        LoyaltyPlanEntity plan = planService.findById(planId);
        return ResponseEntity.ok(enrollmentService.enroll(customer, plan));
    }

    @PostMapping("/customer/{customerId}/plan/{planId}/levels/upgrade-to-level/{levelId}")
    public ResponseEntity<EnrollmentEntity> upgradeLevel(@PathVariable Integer customerId,
                                                         @PathVariable Integer planId,
                                                         @PathVariable Integer levelId) {
        CustomerEntity customer = customerService.findById(customerId);
        LoyaltyPlanEntity plan = planService.findById(planId);
        return ResponseEntity.ok(enrollmentService.upgradeLevel(customer, plan, levelId));
    }
    @GetMapping("/enrollments")
    public List<EnrollmentEntity> getEnrollments(){return this.enrollmentService.getEnrollments();}
//    @GetMapping("/enrollments-by-plan")
//    public List<EnrollmentEntity> getEnrollmentsByPlan(LoyaltyPlanEntity loyaltyPlan){return this.enrollmentService.getEnrollmentsByPlan(loyaltyPlan);}

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
