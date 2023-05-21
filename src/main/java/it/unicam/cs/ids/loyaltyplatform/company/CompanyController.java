package it.unicam.cs.ids.loyaltyplatform.company;

import it.unicam.cs.ids.loyaltyplatform.company.CompanyEntity;
import it.unicam.cs.ids.loyaltyplatform.company.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/api")
public class CompanyController {
    private final CompanyService companyService;
    @Autowired
    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/company")
    public List<CompanyEntity> getCompanies(){
        return this.companyService.getCompanies();
    }
    @GetMapping("/company/{id}")
    public CompanyEntity getCompanyById(@PathVariable Integer id){
        return this.companyService.getCompanyById(id);
    }

    //prima si chiamava addNewCompany ho messo saveCompany per rimanere in linea con gli altri controller
    @PostMapping("/company/register")
    public void saveCompany(@RequestBody CompanyEntity company){
        this.companyService.saveCompany(company);
    }

    @DeleteMapping("/company/delete/{id}")
    public void deleteCompany(@PathVariable Integer id){
        this.companyService.deleteCompany(id);
    }


}
