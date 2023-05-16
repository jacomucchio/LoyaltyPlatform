package it.unicam.cs.ids.loyaltyplatform.loyaltyPlan;

import it.unicam.cs.ids.loyaltyplatform.transaction.TransactionEntity;
import it.unicam.cs.ids.loyaltyplatform.enrollment.EnrollmentEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("membership")
public class MembershipLoyaltyPlan extends LoyaltyPlanEntity {
    private double membershipFee;
    private int membershipDuration;

    public MembershipLoyaltyPlan(String name, double membershipFee, int membershipDuration) {
        super(name);
        this.membershipFee = membershipFee;
        this.membershipDuration = membershipDuration;
    }

    public MembershipLoyaltyPlan(double membershipFee, int membershipDuration) {
        this.membershipFee = membershipFee;
        this.membershipDuration = membershipDuration;
    }

    public MembershipLoyaltyPlan() {
    }

    @Override
    public EnrollmentEntity applyBenefits(EnrollmentEntity enrollment, TransactionEntity transactionEntity) {
        return null;
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
