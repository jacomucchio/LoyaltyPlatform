package it.unicam.cs.ids.loyaltyplatform.transaction;

import it.unicam.cs.ids.loyaltyplatform.card.CardEntity;
import it.unicam.cs.ids.loyaltyplatform.company.CompanyEntity;
import it.unicam.cs.ids.loyaltyplatform.enrollment.EnrollmentEntity;
import it.unicam.cs.ids.loyaltyplatform.enrollment.EnrollmentRepository;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;

@Service
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final EnrollmentRepository enrollmentRepository;
    @Autowired
    public TransactionService(TransactionRepository transactionRepository,
                              EnrollmentRepository enrollmentRepository) {
        this.transactionRepository = transactionRepository;
        this.enrollmentRepository = enrollmentRepository;
    }
    public List<TransactionEntity> findAll(){return transactionRepository.findAll();}

    public TransactionEntity findById(Integer id){
        return transactionRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("Transaction not found with id: "+id));
    }

    public void save(TransactionEntity transactionEntity){transactionRepository.save(transactionEntity);}
    public void deleteById(Integer id){transactionRepository.deleteById(id);}

    public TransactionEntity validateTransaction(CompanyEntity company, CardEntity card, double amount) {
        TransactionEntity transactionEntity = new TransactionEntity(amount, company, card);
        List<EnrollmentEntity> enrollments = new ArrayList<>(card.getCardOwner().getEnrollments());

        for (EnrollmentEntity enrollment : enrollments) {
            if (enrollment.getPlan().getCompany().equals(company)) {
                enrollmentRepository.save(enrollment.getPlan().applyBenefits(enrollment, transactionEntity));
            }
        }
        this.save(transactionEntity);
        return transactionEntity;
    }
    public Collection<? extends TransactionEntity> getTransactionsByCompany(CompanyEntity company) {
        return transactionRepository.findByCompany(company);
    }
    public void delete(TransactionEntity transaction){ this.transactionRepository.delete(transaction);}
}


