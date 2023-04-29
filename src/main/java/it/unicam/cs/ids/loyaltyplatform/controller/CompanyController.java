package it.unicam.cs.ids.loyaltyplatform.controller;

import it.unicam.cs.ids.loyaltyplatform.persistence.entity.CompanyEntity;
import it.unicam.cs.ids.loyaltyplatform.service.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
/*
    TODO:
        -Controlla annotazione @DeleteMapping e @PathVariable

 */
@RestController
@RequestMapping(path = "/company")
public class CompanyController {
    private final CompanyService companyService;
    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }
    @GetMapping
    public List<CompanyEntity> getCompanies(){
        return this.companyService.getCompanies();
    }
    @GetMapping
    public CompanyEntity getCompanyById(Integer id){
        return this.companyService.getCompanyById(id);
    }
    @PostMapping
    public void registerNewCompany(@RequestBody CompanyEntity company){
        this.companyService.addNewCompany(company);
    }
    //
    @DeleteMapping
    public void deleteCompany(CompanyEntity company){
        this.companyService.deleteCompany(company);
    }
}
