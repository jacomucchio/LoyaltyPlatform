package it.unicam.cs.ids.loyaltyplatform.enrollment;

import it.unicam.cs.ids.loyaltyplatform.customer.CustomerEntity;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.PointLoyaltyPlan;
import it.unicam.cs.ids.loyaltyplatform.reward.RewardEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import java.util.List;

@Entity
@DiscriminatorValue("point")
public class PointEnrollment extends EnrollmentEntity{
    private int points;
    @ManyToMany(cascade = CascadeType.DETACH)
    @JoinTable(
            name = "obtainedRewards",
            joinColumns = @JoinColumn(name = "point_enrollment_id"),
            inverseJoinColumns = @JoinColumn(name = "reward_id")
    )
    private List<RewardEntity> obtainedRewards;

    public PointEnrollment(CustomerEntity customer, PointLoyaltyPlan plan) {
        super(customer, plan);
        this.points = 0;
    }

    public PointEnrollment() {
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public List<RewardEntity> getObtainedRewards() {
        return obtainedRewards;
    }

    public void setObtainedRewards(List<RewardEntity> obtainedRewards) {
        this.obtainedRewards = obtainedRewards;
    }

}
