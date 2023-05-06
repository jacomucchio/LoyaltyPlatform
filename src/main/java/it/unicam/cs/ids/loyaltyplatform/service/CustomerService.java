package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.persistence.entity.CustomerEntity;
import it.unicam.cs.ids.loyaltyplatform.persistence.entity.LoyaltyPlanEntity;
import it.unicam.cs.ids.loyaltyplatform.persistence.repository.CustomerRepository;
import it.unicam.cs.ids.loyaltyplatform.persistence.repository.LoyaltyPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final LoyaltyPlanRepository loyaltyPlanRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, LoyaltyPlanRepository loyaltyPlanRepository)
    {

        this.customerRepository=customerRepository;
        this.loyaltyPlanRepository= loyaltyPlanRepository;
    }



    public List<CustomerEntity> findAll()
    {
        return customerRepository.findAll();
    }

    public CustomerEntity findById(Integer id)
    {
        return customerRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Customer not found with id: "+id));
    }

    public void save(CustomerEntity customerEntity){
        customerRepository.save(customerEntity);
    }

    public void deleteById(Integer id){
        customerRepository.deleteById(id);
    }

    public void addLoyaltyPlan(Integer customerId, Integer planId)
    {
        CustomerEntity customer=customerRepository.findById(customerId)
                .orElseThrow(()-> new NoSuchElementException("Customer not found with id: "+customerId));
        LoyaltyPlanEntity loyaltyPlan= loyaltyPlanRepository.findById(planId)
                .orElseThrow(()-> new NoSuchElementException("Plan not found with id: "+planId));
        customer.getLoyaltyPlanList().add(loyaltyPlan);
        loyaltyPlan.getEnrolledCustomers().add(customer);
        /*
        customerRepository.findById(customerId).get().getLoyaltyPlanList()
                .add(loyaltyPlanRepository.findById(planId).get());
        loyaltyPlanRepository.findById(planId).get().getEnrolledCustomers()
                .add(customerRepository.findById(customerId).get());

         */
        customerRepository.save(customer);
        loyaltyPlanRepository.save(loyaltyPlan);
    }



}
