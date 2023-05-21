package it.unicam.cs.ids.loyaltyplatform.reward;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class RewardService {
    private final RewardRepository rewardRepository;

    @Autowired
    public RewardService(RewardRepository rewardRepository) {
        this.rewardRepository = rewardRepository;
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
}
