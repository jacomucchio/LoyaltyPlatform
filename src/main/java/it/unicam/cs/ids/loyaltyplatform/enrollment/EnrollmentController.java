package it.unicam.cs.ids.loyaltyplatform.enrollment;

import it.unicam.cs.ids.loyaltyplatform.customer.CustomerEntity;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanEntity;
import it.unicam.cs.ids.loyaltyplatform.enrollment.EnrollmentEntity;
import it.unicam.cs.ids.loyaltyplatform.customer.CustomerService;
import it.unicam.cs.ids.loyaltyplatform.enrollment.EnrollmentService;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api")
public class EnrollmentController {
    private final EnrollmentService enrollmentService;
    private final CustomerService customerService;
    private final LoyaltyPlanService planService;
    @Autowired
    public EnrollmentController(EnrollmentService enrollmentService, CustomerService customerService,
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

}
