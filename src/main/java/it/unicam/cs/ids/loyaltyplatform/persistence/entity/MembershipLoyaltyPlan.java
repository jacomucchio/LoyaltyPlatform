package it.unicam.cs.ids.loyaltyplatform.persistence.entity;

import it.unicam.cs.ids.loyaltyplatform.persistence.entity.LoyaltyPlanEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("membership")
public class MembershipLoyaltyPlan extends LoyaltyPlanEntity {
    private double membershipFee;
    private int membershipDuration;

    public MembershipLoyaltyPlan(String name, String type, double membershipFee, int membershipDuration) {
        super(name, type);
        this.membershipFee = membershipFee;
        this.membershipDuration = membershipDuration;
    }

    public MembershipLoyaltyPlan(double membershipFee, int membershipDuration) {
        this.membershipFee = membershipFee;
        this.membershipDuration = membershipDuration;
    }

    public MembershipLoyaltyPlan() {
    }

    public double getMembershipFee() {
        return membershipFee;
    }

    public void setMembershipFee(double membershipFee) {
        this.membershipFee = membershipFee;
    }

    public int getMembershipDuration() {
        return membershipDuration;
    }

    public void setMembershipDuration(int membershipDuration) {
        this.membershipDuration = membershipDuration;
    }
}
