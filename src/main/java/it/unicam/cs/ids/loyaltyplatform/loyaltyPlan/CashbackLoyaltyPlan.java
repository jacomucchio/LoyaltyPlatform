package it.unicam.cs.ids.loyaltyplatform.loyaltyPlan;

import it.unicam.cs.ids.loyaltyplatform.transaction.TransactionEntity;
import it.unicam.cs.ids.loyaltyplatform.enrollment.EnrollmentEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
//TODO METTERE COSTRUTTORE, INSERIRLO CORRETTAMENTE
@Entity
@DiscriminatorValue("cashback")
public class CashbackLoyaltyPlan extends LoyaltyPlanEntity {
    private double cashbackPercentage;

    private double maxCashbackAmount;


    public CashbackLoyaltyPlan(String name, double cashbackPercentage, double maxCashbackAmount) {
        super(name);
        this.cashbackPercentage = cashbackPercentage;
        this.maxCashbackAmount = maxCashbackAmount;
    }

    public CashbackLoyaltyPlan(double cashbackPercentage, double maxCashbackAmount) {
        this.cashbackPercentage = cashbackPercentage;
        this.maxCashbackAmount = maxCashbackAmount;
    }

    public CashbackLoyaltyPlan() {
    }

    @Override
    public EnrollmentEntity applyBenefits(EnrollmentEntity enrollment, TransactionEntity transactionEntity) {
        return null;
    }

    public double getCashbackPercentage() {
        return cashbackPercentage;
    }

    public void setCashbackPercentage(double cashbackPercentage) {
        this.cashbackPercentage = cashbackPercentage;
    }

    public double getMaxCashbackAmount() {
        return maxCashbackAmount;
    }

    public void setMaxCashbackAmount(double maxCashbackAmount) {
        this.maxCashbackAmount = maxCashbackAmount;
    }
}