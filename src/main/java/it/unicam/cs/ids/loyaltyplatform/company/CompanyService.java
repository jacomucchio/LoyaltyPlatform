package it.unicam.cs.ids.loyaltyplatform.company;

import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;

    @Autowired
    public CompanyService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public List<CompanyEntity> getCompanies(){
        return this.companyRepository.findAll();
    }

    public CompanyEntity getCompanyById(Integer id){
        return this.companyRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }
    //prima si chiamava addNewCompany ho messo saveCompany per rimanere in linea con gli altri controller
    public void saveCompany(CompanyEntity company) {
        this.companyRepository.save(company);
    }

    public void deleteCompany(Integer id) {
        this.companyRepository.deleteById(id);
    }



//        public LoyaltyPlanEntity getLoyaltyPlanById(Integer companyId, Integer planId) {
//            CompanyEntity company = getCompanyById(companyId);
//
//            List<LoyaltyPlanEntity> loyaltyPlans = company.getLoyaltyPlans();
//            for (LoyaltyPlanEntity loyaltyPlan : loyaltyPlans) {
//                if (loyaltyPlan.getId().equals(planId)) {
//                    return loyaltyPlan;
//                }
//            }
//            throw new IllegalArgumentException("Loyalty plan not found with ID: " + planId);
//        }
    }

