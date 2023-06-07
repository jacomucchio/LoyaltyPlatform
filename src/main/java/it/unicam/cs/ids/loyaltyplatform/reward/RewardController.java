package it.unicam.cs.ids.loyaltyplatform.reward;

import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LoyaltyPlanEntity;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping(path = "/api")
public class RewardController {
    private final RewardService rewardService;

    @Autowired
    public RewardController(RewardService rewardService) {
        this.rewardService = rewardService;

    }

    @GetMapping("/rewards")
    public List<RewardEntity> getRewards(){return this.rewardService.getRewards();}


    @GetMapping("/reward/{id}")
    public RewardEntity getRewardById(@PathVariable Integer id){
        return this.rewardService.getRewardById(id);
    }


    @PostMapping("/reward/save")
    public void saveReward(@RequestBody RewardEntity rewardEntity){ this.rewardService.saveReward(rewardEntity); }

    @PostMapping("/company/{companyId}/loyalty-plan/{planId}/reward/add")
    public ResponseEntity<LoyaltyPlanEntity> addRewardToPlan(@PathVariable Integer companyId, @PathVariable Integer planId,
                                                             @Valid @RequestBody  RewardEntity rewardEntity){
        rewardService.addRewardToPlan(companyId, planId, rewardEntity);
        return ResponseEntity.ok().build();
    }
    @Transactional
    @DeleteMapping("/reward/delete/{id}")
    public ResponseEntity<String> deleteReward(@PathVariable Integer id){
        this.rewardService.deleteReward(id);
        return ResponseEntity.ok("Reward removed successfully");
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgumentException(IllegalArgumentException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }

    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNoSuchElementException(NoSuchElementException ex) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ex.getMessage());
    }
}
