package it.unicam.cs.ids.loyaltyplatform.level;
import it.unicam.cs.ids.loyaltyplatform.company.CompanyEntity;
import it.unicam.cs.ids.loyaltyplatform.company.CompanyService;
import it.unicam.cs.ids.loyaltyplatform.enrollment.EnrollmentRepository;
import it.unicam.cs.ids.loyaltyplatform.enrollment.LevelEnrollment;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LevelLoyaltyPlan;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanEntity;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Objects;

@Service
public class LevelService {
    private final LevelRepository levelRepository;
    private final LoyaltyPlanService loyaltyPlanService;
    private final CompanyService companyService;

    private final EnrollmentRepository enrollmentRepository;
    @Autowired

    public LevelService(LevelRepository levelRepository, LoyaltyPlanService loyaltyPlanService,
                        CompanyService companyService, EnrollmentRepository enrollmentRepository)
    {
        this.levelRepository=levelRepository;
        this.loyaltyPlanService=loyaltyPlanService;
        this.companyService=companyService;
        this.enrollmentRepository=enrollmentRepository;
    }

    public LevelEntity findById(Integer id)
    {
        return levelRepository.findById(id)
                .orElseThrow(()-> new NoSuchElementException("level not found with id: "+id));
    }

    public void addLevelToPlan(Integer planId, LevelEntity level) {
        LoyaltyPlanEntity plan = loyaltyPlanService.findById(planId);
        if(plan instanceof LevelLoyaltyPlan)
        {
            LevelLoyaltyPlan levelPlan = (LevelLoyaltyPlan) plan;
            level.setPlan(levelPlan);
            levelPlan.getLevels().add(level);
            loyaltyPlanService.save(levelPlan);
        }
    }

    public void save(LevelEntity levelEntity){levelRepository.save(levelEntity);}

    public LevelEntity findByIdAndPlan_Id(Integer levelId, Integer planId)
    {
        return levelRepository.findByIdAndPlan_Id(levelId,planId)
                .orElseThrow(()-> new NoSuchElementException("level not found"));
    }
    public void deleteLevel(Integer companyId, Integer planId, Integer levelId) {
        //ottengo l'azienda
        CompanyEntity company = companyService.getCompanyById(companyId);
        // Controlla se il piano esiste nell'azienda
        LoyaltyPlanEntity plan= loyaltyPlanService.findByIdAndCompanyId(planId,companyId);
        //controlla se il livello esiste nel piano
        if(plan instanceof LevelLoyaltyPlan levelPlan){
            LevelEntity level = this.findByIdAndPlan_Id(levelId,planId);
            if(levelPlan.getBaseLevel().equals(level)) throw new DataIntegrityViolationException
                    ("base level cannot be deleted");
            // Recupera le iscrizioni associate al livello
            List<LevelEnrollment> enrollments = enrollmentRepository.findByCurrentLevel(level);
            // Aggiorna il livello degli utenti iscritti
            for (LevelEnrollment enrollment : enrollments) {
                enrollment.setCurrentLevel(levelPlan.getBaseLevel()); // Imposta il livello al piano base
            }
            levelRepository.delete(level);
        } else throw new IllegalArgumentException("plan is not a level plan");
    }
}
