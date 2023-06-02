package it.unicam.cs.ids.loyaltyplatform.reward;


import it.unicam.cs.ids.loyaltyplatform.company.CompanyEntity;
import it.unicam.cs.ids.loyaltyplatform.company.CompanyService;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanEntity;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanService;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.PointLoyaltyPlan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
@Service
public class RewardService {
    private final RewardRepository rewardRepository;
    private final CompanyService companyService;
    private final LoyaltyPlanService loyaltyPlanService;

    @Autowired
    public RewardService(RewardRepository rewardRepository, CompanyService companyService, LoyaltyPlanService loyaltyPlanService) {
        this.rewardRepository = rewardRepository;
        this.companyService = companyService;
        this.loyaltyPlanService = loyaltyPlanService;
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
        this.rewardRepository.deleteById(id);
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
