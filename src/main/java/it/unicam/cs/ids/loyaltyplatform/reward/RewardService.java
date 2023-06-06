package it.unicam.cs.ids.loyaltyplatform.reward;


import it.unicam.cs.ids.loyaltyplatform.company.CompanyEntity;
import it.unicam.cs.ids.loyaltyplatform.company.CompanyService;
import it.unicam.cs.ids.loyaltyplatform.enrollment.EnrollmentService;
import it.unicam.cs.ids.loyaltyplatform.enrollment.PointEnrollment;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanEntity;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanService;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.PointLoyaltyPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class RewardService {
    private final RewardRepository rewardRepository;
    private final CompanyService companyService;
    private final LoyaltyPlanService loyaltyPlanService;
    private final EnrollmentService enrollmentService;

    @Autowired
    public RewardService(RewardRepository rewardRepository, CompanyService companyService, LoyaltyPlanService loyaltyPlanService, @Lazy EnrollmentService enrollmentService) {
        this.rewardRepository = rewardRepository;
        this.companyService = companyService;
        this.loyaltyPlanService = loyaltyPlanService;
        this.enrollmentService = enrollmentService;
    }

    public List<RewardEntity> getRewards() { return this.rewardRepository.findAll(); }

    public RewardEntity getRewardById(Integer id){
        return this.rewardRepository.findById(id).
                orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public void saveReward(RewardEntity rewardEntity) {
        this.rewardRepository.save(rewardEntity);
    }

    public void deleteReward(Integer id) {
        RewardEntity reward = this.getRewardById(id);

        PointLoyaltyPlan loyaltyPlan = reward.getPointLoyaltyPlan();
        if (loyaltyPlan != null) {
            loyaltyPlan.removeReward(reward);
            loyaltyPlanService.save(loyaltyPlan);
        }

        List<PointEnrollment> enrollments = enrollmentService.getEnrollmentsByReward(reward);
        if (!enrollments.isEmpty()) {
            for (PointEnrollment enrollment : enrollments) {
                enrollment.getObtainedRewards().remove(reward);
                enrollmentService.save(enrollment);
            }
        }

        rewardRepository.deleteById(id);
    }

    public void addRewardToPlan(Integer companyId, Integer planId, RewardEntity rewardEntity) {
        CompanyEntity company = companyService.getCompanyById(companyId);
        LoyaltyPlanEntity loyaltyPlan = loyaltyPlanService.findById(planId);

        if (loyaltyPlan instanceof PointLoyaltyPlan) {
            PointLoyaltyPlan pointLoyaltyPlan = (PointLoyaltyPlan) loyaltyPlan;
            pointLoyaltyPlan.addReward(rewardEntity);
            companyService.save(company);
            loyaltyPlanService.save(loyaltyPlan);
        } else {
            throw new IllegalArgumentException("Invalid loyalty plan type.");
        }
    }
}
