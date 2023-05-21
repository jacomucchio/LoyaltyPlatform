package it.unicam.cs.ids.loyaltyplatform.customer;

import it.unicam.cs.ids.loyaltyplatform.customer.CustomerEntity;
import it.unicam.cs.ids.loyaltyplatform.enrollment.EnrollmentEntity;
import it.unicam.cs.ids.loyaltyplatform.customer.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class CustomerController {
    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService)
    {
        this.customerService=customerService;
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
    @DeleteMapping("/customer/{id}")
    public void deleteById(@PathVariable Integer id)
    {
        customerService.deleteById(id);
    }

    /*
    TODO : - il metodo per la creazione del piano dovrebbe essere gestito dal controller di EnrollmentEntity e non
             dal controller di Customer. /api/customer/{customerId}/enroll-to-plan/{planId}
     */

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

}
