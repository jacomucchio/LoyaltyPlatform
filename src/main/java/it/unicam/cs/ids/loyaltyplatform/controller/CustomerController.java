package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.persistence.entity.CustomerEntity;
import it.unicam.cs.ids.loyaltyplatform.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//TODO controllare service di planEnrollment non so se va qua o boh
@RestController
@RequestMapping("/customers")
public class CustomerController {
    private final CustomerService customerService;
    @Autowired
    public CustomerController(CustomerService customerService)
    {
        this.customerService=customerService;
    }

    @GetMapping("/")
    public List<CustomerEntity> findAll()
    {
        return customerService.findAll();
    }
    @GetMapping("/{id}")
    public CustomerEntity findById(@PathVariable Integer id)
    {
        return customerService.findById(id);
    }
    @PostMapping("/")
    public void save(@RequestBody CustomerEntity customer)
    {
        customerService.save(customer);
    }
    @DeleteMapping("/{id}")
    public void deleteById(@PathVariable Integer id)
    {
        customerService.deleteById(id);
    }

    @PostMapping("/{customerId}/addLoyaltyPlan/{planId}")
    public ResponseEntity<String> addLoyaltyPlan(@PathVariable Integer customerId, @PathVariable Integer planId) {
        customerService.addLoyaltyPlan(customerId,planId);
        return ResponseEntity.ok("Loyalty plan subscription successful");
    }

}
