package it.unicam.cs.ids.loyaltyplatform.company;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


import java.util.List;

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
        this.companyRepository.save(company);
    }

    public void deleteCompany(Integer id) {
        this.companyRepository.deleteById(id);
    }


}