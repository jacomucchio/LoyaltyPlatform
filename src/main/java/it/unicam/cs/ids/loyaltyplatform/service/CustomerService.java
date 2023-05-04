package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.persistence.entity.CustomerEntity;
import it.unicam.cs.ids.loyaltyplatform.persistence.entity.PlanEnrollmentEntity;
import it.unicam.cs.ids.loyaltyplatform.persistence.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    @Autowired
    public CustomerService(CustomerRepository customerRepository)
    {
        this.customerRepository=customerRepository;
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



}
