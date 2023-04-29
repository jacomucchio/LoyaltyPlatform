package it.unicam.cs.ids.loyaltyplatform.service;

import it.unicam.cs.ids.loyaltyplatform.persistence.entity.CompanyEntity;
import it.unicam.cs.ids.loyaltyplatform.persistence.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;
import java.util.Optional;

/*
    TODO:
        -Controlla i query/find method
 */
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


    public void addNewCompany(CompanyEntity company) {
        Optional<CompanyEntity> optionalCompany = this.companyRepository.findById(company.getId());
        if (optionalCompany.isPresent()){
            throw new IllegalStateException("Company is already registered");
        }else
            companyRepository.save(company);
    }

    public void deleteCompany(CompanyEntity company) {
        boolean companyExists = this.companyRepository.existsById(company.getId());
        if (!companyExists){
            throw new IllegalStateException(
                    "Company with id: " +company.getId()+
                            "\n named: "+company.getName()+" does not exist");
        }else
            this.companyRepository.deleteById(company.getId());
    }


}
