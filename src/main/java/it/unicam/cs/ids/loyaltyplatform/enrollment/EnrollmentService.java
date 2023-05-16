package it.unicam.cs.ids.loyaltyplatform.enrollment;

import it.unicam.cs.ids.loyaltyplatform.customer.CustomerEntity;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository) {this.enrollmentRepository=enrollmentRepository;}

    public List<EnrollmentEntity> findAll(){return enrollmentRepository.findAll();}

    public EnrollmentEntity findById(Integer id)
    {
        return enrollmentRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("Enrollment not found with id: "+id));
    }

    public void deleteById(Integer id){enrollmentRepository.deleteById(id);}

    public void save(EnrollmentEntity enrollment){enrollmentRepository.save(enrollment);}

    public EnrollmentEntity enroll(CustomerEntity customer, LoyaltyPlanEntity plan) {
        EnrollmentEntity enrollment = null;
        switch (plan.getDiscriminatorValue()) {
            case "point":
                enrollment = new PointEnrollment(customer, (PointLoyaltyPlan) plan);
                break;
            case "level":
                enrollment = new LevelEnrollment(customer, (LevelLoyaltyPlan) plan);
                break;
            case "cashback":
                enrollment = new CashbackEnrollment(customer, (CashbackLoyaltyPlan) plan);
                break;
            case "coalition":
                enrollment = new CoalitionEnrollment(customer, (CoalitionLoyaltyPlan) plan);
                break;
            case "membership":
                enrollment = new MembershipEnrollment(customer, (MembershipLoyaltyPlan) plan);
                break;
        }
        customer.getEnrollments().add(enrollment);
        plan.getEnrollments().add(enrollment);
        return enrollmentRepository.save(enrollment);
    }

}
