package it.unicam.cs.ids.loyaltyplatform.customer;

import it.unicam.cs.ids.loyaltyplatform.enrollment.EnrollmentEntity;
import it.unicam.cs.ids.loyaltyplatform.enrollment.EnrollmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private final CustomerService customerService;
    private final EnrollmentService enrollmentService;
    @Autowired
    public CustomerController(CustomerService customerService, EnrollmentService enrollmentService)
    {
        this.customerService=customerService;
        this.enrollmentService = enrollmentService;
    }

    @GetMapping("/customer")
    public List<CustomerEntity> findAll()
    {
        return customerService.findAll();
    }
    @GetMapping("/customer/{id}")
    public CustomerEntity findById(@PathVariable Integer id)
    {
        return customerService.findById(id);
    }
    @PostMapping("/customer/register")
    public void save(@RequestBody CustomerEntity customer)
    {
        customerService.save(customer);
    }
    @DeleteMapping("/customer/{id}/delete")
    public ResponseEntity<String> deleteCustomer(@PathVariable Integer id){
        this.customerService.deleteCustomer(id);
        return ResponseEntity.status(HttpStatus.OK).body("Customer deleted successfully");
    }
    @PostMapping("/customer/{customerId}/add-plan/{planId}")
    public ResponseEntity<String> addLoyaltyPlan(@PathVariable Integer customerId, @PathVariable Integer planId) {
        customerService.addLoyaltyPlan(customerId,planId);
        return ResponseEntity.ok("Loyalty plan subscription successful");
    }
    @GetMapping("/customer/{customerId}/enrollments")
    public ResponseEntity<List<EnrollmentEntity>> findEnrollments(@PathVariable Integer customerId)
    {
        return ResponseEntity.ok(customerService.findEnrollments(customerId));

    }
    @PostMapping("/customer/enrollments/{enrollmentId}/rewards/{rewardId}/redeem")
    public ResponseEntity<EnrollmentEntity> redeemReward(@PathVariable Integer enrollmentId,
                                                         @PathVariable Integer rewardId){
        return ResponseEntity.ok(enrollmentService.redeemReward(enrollmentId, rewardId));

    }

}
