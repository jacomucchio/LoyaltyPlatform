package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.persistence.entity.CompanyEntity;
import it.unicam.cs.ids.loyaltyplatform.persistence.entity.LoyaltyPlanEntity;
import it.unicam.cs.ids.loyaltyplatform.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/company")
public class CompanyController {
    private final CompanyService companyService;
    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/list")
    public List<CompanyEntity> getCompanies(){
        return this.companyService.getCompanies();
    }
    @GetMapping("/{id}")
    public CompanyEntity getCompanyById(@PathVariable Integer id){
        return this.companyService.getCompanyById(id);
    }
    @PostMapping("/register")
    public void registerNewCompany(@RequestBody CompanyEntity company){
        this.companyService.addNewCompany(company);
    }

    @DeleteMapping("/delete/{id}")
    public void deleteCompany(@PathVariable Integer id){
        this.companyService.deleteCompany(id);
    }

    @PostMapping("/addPlan")
    public void addPlan(){}

}
