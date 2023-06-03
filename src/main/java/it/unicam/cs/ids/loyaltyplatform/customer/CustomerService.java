package it.unicam.cs.ids.loyaltyplatform.customer;
import it.unicam.cs.ids.loyaltyplatform.card.CardEntity;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanEntity;
import it.unicam.cs.ids.loyaltyplatform.enrollment.EnrollmentEntity;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;
    private final LoyaltyPlanRepository loyaltyPlanRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public CustomerService(CustomerRepository customerRepository, LoyaltyPlanRepository loyaltyPlanRepository, BCryptPasswordEncoder bCryptPasswordEncoder)
    {

        this.customerRepository=customerRepository;
        this.loyaltyPlanRepository= loyaltyPlanRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
            Optional<CustomerEntity> customerByEmail = customerRepository.findByEmailAddress(customerEntity.getEmailAddress());
        if(customerByEmail.isPresent()) {
            throw new IllegalStateException("Email already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(customerEntity.getPassword());
        customerEntity.setPassword(encodedPassword);
        CardEntity card = new CardEntity(customerEntity);
        customerEntity.setCard(card);
        customerRepository.save(customerEntity);
    }
    public void addLoyaltyPlan(Integer customerId, Integer planId)
    {
        CustomerEntity customer=customerRepository.findById(customerId)
                .orElseThrow(()-> new NoSuchElementException("Customer not found with id: "+customerId));
        LoyaltyPlanEntity loyaltyPlan= loyaltyPlanRepository.findById(planId)
                .orElseThrow(()-> new NoSuchElementException("Plan not found with id: "+planId));
        customerRepository.save(customer);
        loyaltyPlanRepository.save(loyaltyPlan);
    }

    public List<EnrollmentEntity> findEnrollments(Integer customerId)
    {
        return customerRepository.findById(customerId).get().getEnrollments();
    }

    public void deleteCustomer(Integer id) {
        CustomerEntity customer = customerRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Customer with id " + id + " not found: "));
        this.customerRepository.deleteById(id);
    }

}
