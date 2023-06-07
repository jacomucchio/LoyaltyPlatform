package it.unicam.cs.ids.loyaltyplatform.company;
import it.unicam.cs.ids.loyaltyplatform.enrollment.EnrollmentEntity;
import it.unicam.cs.ids.loyaltyplatform.enrollment.EnrollmentService;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanEntity;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanService;
import it.unicam.cs.ids.loyaltyplatform.transaction.TransactionEntity;
import it.unicam.cs.ids.loyaltyplatform.transaction.TransactionService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final LoyaltyPlanService loyaltyPlanService;
    private final EnrollmentService enrollmentService;
    private final TransactionService transactionService;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, BCryptPasswordEncoder bCryptPasswordEncoder, @Lazy LoyaltyPlanService loyaltyPlanService, @Lazy EnrollmentService enrollmentService, TransactionService transactionService) {
        this.companyRepository = companyRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
        this.loyaltyPlanService = loyaltyPlanService;
        this.enrollmentService = enrollmentService;
        this.transactionService = transactionService;
    }

    public List<CompanyEntity> getCompanies(){
        return this.companyRepository.findAll();
    }

    public CompanyEntity getCompanyById(Integer id){
        return this.companyRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void saveCompany(CompanyEntity company) {
        Optional<CompanyEntity> companyByEmail = companyRepository.findByEmailAddress(company.getEmailAddress());
        if(companyByEmail.isPresent()){
            throw new IllegalStateException("Email already taken");
        }
        String encodedPassword = bCryptPasswordEncoder.encode(company.getPassword());
        company.setPassword(encodedPassword);
        this.companyRepository.save(company);
    }
    public void save(CompanyEntity company) {companyRepository.save(company);}

    public void deleteCompany(Integer id) {
        CompanyEntity company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company with id " + id + " not found: "));
        List<LoyaltyPlanEntity> companyPlans = loyaltyPlanService.findByCompanyId(id);
        List<EnrollmentEntity> enrollmentsOfPlan = new ArrayList<>();
        Collection<? extends TransactionEntity> companyTransactions = transactionService.getTransactionsByCompany(company);

        if (!companyPlans.isEmpty()) {
            for (LoyaltyPlanEntity loyaltyPlan : companyPlans) {
                enrollmentsOfPlan.addAll(enrollmentService.getEnrollmentsByPlan(loyaltyPlan));
                loyaltyPlanService.delete(loyaltyPlan);
            }
        }if (!enrollmentsOfPlan.isEmpty()){
            for (EnrollmentEntity enrollment :
                    enrollmentsOfPlan) {
                enrollmentService.delete(enrollment);
            }
        }if (!companyTransactions.isEmpty()){
            for (TransactionEntity transaction :
                    companyTransactions) {
                transactionService.delete(transaction);
            }
        }
        this.companyRepository.deleteById(id);
    }

    public CompanyEntity updateCompany(Integer id, CompanyEntity updatedCompany) {

        CompanyEntity existingCompany = companyRepository.findByEmailAddress(updatedCompany.getEmailAddress())
                .orElse(null);

        if (existingCompany != null && !existingCompany.getId().equals(id))
        {
                throw new IllegalArgumentException("Email already taken");
        }

        CompanyEntity company = companyRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Company with id " + id + " not found"));

        String encodedPassword = bCryptPasswordEncoder.encode(updatedCompany.getPassword());

        company.setName(updatedCompany.getName());
        company.setEmailAddress(updatedCompany.getEmailAddress());
        company.setPassword(encodedPassword);

        return companyRepository.save(company);
    }



}

