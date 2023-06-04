package it.unicam.cs.ids.loyaltyplatform.company;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.List;
import java.util.Optional;

@Service
public class CompanyService {

    private final CompanyRepository companyRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public CompanyService(CompanyRepository companyRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.companyRepository = companyRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
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
        this.companyRepository.deleteById(id);
    }
    
}

