package it.unicam.cs.ids.loyaltyplatform.customer;

import it.unicam.cs.ids.loyaltyplatform.card.CardEntity;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanEntity;
import it.unicam.cs.ids.loyaltyplatform.enrollment.EnrollmentEntity;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanRepository;
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
        CardEntity card = new CardEntity(customerEntity);
        customerEntity.setCard(card);
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
        //da aggiungere il metodo per eseguire l'iscrizione
        customerRepository.save(customer);
        loyaltyPlanRepository.save(loyaltyPlan);
    }

    public List<EnrollmentEntity> findEnrollments(Integer customerId)
    {
        return customerRepository.findById(customerId).get().getEnrollments();
    }

}
