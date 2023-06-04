package it.unicam.cs.ids.loyaltyplatform.company;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    @GetMapping("/companies")
    public List<CompanyEntity> getCompanies(){
        return this.companyService.getCompanies();
    }
    @GetMapping("/company/{id}")
    public CompanyEntity getCompanyById(@PathVariable Integer id){
        return this.companyService.getCompanyById(id);
    }
    @PostMapping("/company/register")
    public void saveCompany(@RequestBody CompanyEntity company){
        this.companyService.saveCompany(company);
    }
    @PostMapping
    public void save(@RequestBody CompanyEntity company){this.companyService.save(company);}

    @DeleteMapping("/company/delete/{id}")
    public ResponseEntity<String> deleteCompany(@PathVariable Integer id){
        this.companyService.deleteCompany(id);
        return ResponseEntity.status(HttpStatus.OK).body("Company deleted successfully");
    }

    @PutMapping("/company/{id}/update")
    public ResponseEntity<CompanyEntity> updateCompany(@PathVariable Integer id, @RequestBody CompanyEntity updatedCompany) {
        CompanyEntity company = companyService.updateCompany(id, updatedCompany);
        return ResponseEntity.ok(company);
    }
    
}
