package it.unicam.cs.ids.loyaltyplatform.enrollment;

import it.unicam.cs.ids.loyaltyplatform.customer.CustomerEntity;
import it.unicam.cs.ids.loyaltyplatform.level.LevelEntity;
import it.unicam.cs.ids.loyaltyplatform.level.LevelService;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.*;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.LevelLoyaltyPlan;
import it.unicam.cs.ids.loyaltyplatform.reward.RewardEntity;
import it.unicam.cs.ids.loyaltyplatform.reward.RewardService;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final LevelService levelService;
    private final RewardService rewardService;

    @Autowired
    public EnrollmentService(EnrollmentRepository enrollmentRepository, LevelService levelService, @Lazy RewardService rewardService) {
        this.enrollmentRepository=enrollmentRepository;
        this.levelService=levelService;
        this.rewardService = rewardService;
    }

    public List<EnrollmentEntity> findAll(){return enrollmentRepository.findAll();}

    public EnrollmentEntity findById(Integer id)
    {
        return enrollmentRepository.findById(id).
                orElseThrow(()-> new NoSuchElementException("Enrollment not found with id: "+id));
    }

    public void deleteById(Integer id){enrollmentRepository.deleteById(id);}

    public void save(EnrollmentEntity enrollment){enrollmentRepository.save(enrollment);}

    public EnrollmentEntity enroll(CustomerEntity customer, LoyaltyPlanEntity plan) {
        EnrollmentEntity enrollment = null;
        switch (plan.getDiscriminatorValue()) {
            case "point":
                enrollment = new PointEnrollment(customer, (PointLoyaltyPlan) plan);
                break;
            case "level":
                enrollment = new LevelEnrollment(customer, (LevelLoyaltyPlan) plan);
                break;
            case "cashback":
                enrollment = new CashbackEnrollment(customer, (CashbackLoyaltyPlan) plan);
                break;
            case "coalition":
                enrollment = new CoalitionEnrollment(customer, (CoalitionLoyaltyPlan) plan);
                break;
            case "membership":
                enrollment = new MembershipEnrollment(customer, (MembershipLoyaltyPlan) plan);
                break;
        }
        customer.getEnrollments().add(enrollment);
        plan.getEnrollments().add(enrollment);
        plan.setCustomerCount(plan.getCustomerCount() + 1);
        return enrollmentRepository.save(enrollment);
    }

    public Optional<EnrollmentEntity> getEnrollmentByPlanAndCustomer(LoyaltyPlanEntity plan, CustomerEntity customer) {
        return enrollmentRepository.findByPlanAndCustomer(plan, customer);
    }
    public Collection<? extends EnrollmentEntity> getEnrollmentsByPlan(LoyaltyPlanEntity plan) {
        return enrollmentRepository.findByPlan(plan);
    }
    public void delete(EnrollmentEntity enrollmentEntity){enrollmentRepository.delete(enrollmentEntity);}

    public EnrollmentEntity upgradeLevel(CustomerEntity customer, LoyaltyPlanEntity plan, Integer levelId) {
        EnrollmentEntity enrollment=this.getEnrollmentByPlanAndCustomer(plan,customer)
                .orElseThrow(()->new IllegalArgumentException("customer or plan not found"));
        if(enrollment instanceof LevelEnrollment levelEnrollment)
        {
            LevelEntity level=levelService.findById(levelId);
            if(levelEnrollment.getPoints()>=level.getRequiredPoints())
            {
                levelEnrollment.setCurrentLevel(level);
                this.save(levelEnrollment);
                return enrollment;
            }else throw new IllegalArgumentException("insufficient points");
        }
        return enrollment;
    }

    @Transactional
    public EnrollmentEntity redeemReward(Integer enrollmentId, Integer rewardId) {
        EnrollmentEntity enrollment = this.findById(enrollmentId);
        RewardEntity reward = rewardService.getRewardById(rewardId);

        if (!(enrollment instanceof PointEnrollment)) {
            throw new IllegalArgumentException("Enrollment type not valid");
        }
        PointEnrollment pointEnrollment = (PointEnrollment) enrollment;

        if (pointEnrollment.getPoints() >= reward.getRequiredPoints()){
            pointEnrollment.getObtainedRewards().add(reward);
            pointEnrollment.setPoints(pointEnrollment.getPoints() - reward.getRequiredPoints());
            this.save(pointEnrollment);
            return pointEnrollment;
        }else throw new IllegalArgumentException("Insufficient point to redeem reward");
    }

    public List<PointEnrollment> getEnrollmentsByReward(RewardEntity reward) {
        List<PointEnrollment> enrollmentsByReward = new ArrayList<>();
        List<PointEnrollment> enrollments = this.getAllPointEnrollments();

        for (PointEnrollment enrollment : enrollments) {
            if (enrollment.getObtainedRewards().contains(reward)) {
                enrollmentsByReward.add(enrollment);
            }
        }
        return enrollmentsByReward;
    }

    public List<EnrollmentEntity> getEnrollments() {
        return this.enrollmentRepository.findAll();
    }
    public List<PointEnrollment> getAllPointEnrollments() {
        List<PointEnrollment> pointEnrollments = new ArrayList<>();
        List<EnrollmentEntity> enrollments = getEnrollments();

        for (EnrollmentEntity enrollment : enrollments) {
            if (enrollment instanceof PointEnrollment) {
                pointEnrollments.add((PointEnrollment) enrollment);
            }
        }
        return pointEnrollments;
    }
}
