package it.unicam.cs.ids.loyaltyplatform.loyaltyPlan;

import it.unicam.cs.ids.loyaltyplatform.company.CompanyEntity;
import it.unicam.cs.ids.loyaltyplatform.company.CompanyRepository;
import it.unicam.cs.ids.loyaltyplatform.enrollment.EnrollmentEntity;
import it.unicam.cs.ids.loyaltyplatform.enrollment.EnrollmentRepository;
import it.unicam.cs.ids.loyaltyplatform.enrollment.EnrollmentService;
import it.unicam.cs.ids.loyaltyplatform.enrollment.LevelEnrollment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class LoyaltyPlanService {
    private final LoyaltyPlanRepository loyaltyPlanRepository;
    private final CompanyRepository companyRepository;
    private final EnrollmentService enrollmentService;

    @Autowired
    public LoyaltyPlanService(LoyaltyPlanRepository loyaltyPlanRepository, @Lazy CompanyRepository companyRepository,
                             @Lazy EnrollmentService enrollmentService) {
        this.loyaltyPlanRepository = loyaltyPlanRepository;
        this.companyRepository = companyRepository;
        this.enrollmentService=enrollmentService;
    }

    public LoyaltyPlanEntity createLoyaltyPlan(Integer companyId, LoyaltyPlanEntity loyaltyPlan) {
        CompanyEntity company = companyRepository.findById(companyId).orElseThrow(() ->
                new IllegalArgumentException("Company not found with id " + companyId));
        loyaltyPlan.setCompany(company);
        return loyaltyPlanRepository.save(loyaltyPlan);
    }

    public List<LoyaltyPlanEntity> findAll() {
        return loyaltyPlanRepository.findAll();
    }

    public LoyaltyPlanEntity findById(Integer id){
        return loyaltyPlanRepository.findById(id)
                 .orElseThrow(() -> new NoSuchElementException("Loyalty plan not found with id: " + id));
    }

    public void save(LoyaltyPlanEntity loyaltyPlanEntity) {
        loyaltyPlanRepository.save(loyaltyPlanEntity);
    }

    public void deleteById(Integer companyId, Integer planId) {
        LoyaltyPlanEntity plan = this.findByIdAndCompanyId(planId, companyId);
        List<EnrollmentEntity> enrollments = new ArrayList<>();
        enrollments.addAll(enrollmentService.getEnrollmentsByPlan(plan));
        if(enrollments.isEmpty())throw new IllegalArgumentException("è vuoto");
        for (EnrollmentEntity enrollment : enrollments) {
            if (enrollment instanceof LevelEnrollment levelEnrollment) {
                levelEnrollment.setCurrentLevel(null);
                enrollmentService.delete(levelEnrollment);
            }
        }

        loyaltyPlanRepository.delete(plan);
    }
    public void delete(LoyaltyPlanEntity loyaltyPlanEntity){loyaltyPlanRepository.delete(loyaltyPlanEntity);}

    public List<LoyaltyPlanEntity> findByCompanyId(Integer id) {
        return loyaltyPlanRepository.findByCompanyId(id);
    }

    public LoyaltyPlanEntity findByIdAndCompanyId(Integer planId, Integer companyId) {
        return loyaltyPlanRepository.findByIdAndCompany_Id(planId, companyId).
                orElseThrow(()->new NoSuchElementException("Loyalty plan not found"));
    }

}
