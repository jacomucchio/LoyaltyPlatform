package it.unicam.cs.ids.loyaltyplatform.persistence.entity;

import it.unicam.cs.ids.loyaltyplatform.persistence.entity.LoyaltyPlanEntity;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
//TODO METTERE COSTRUTTORE, INSERIRLO CORRETTAMENTE
@Entity
@DiscriminatorValue("cashback")
public class CashbackLoyaltyPlan extends LoyaltyPlanEntity {
    private double cashbackPercentage;

    private double maxCashbackAmount;


    public CashbackLoyaltyPlan(String name, String type, double cashbackPercentage, double maxCashbackAmount) {
        super(name, type);
        this.cashbackPercentage = cashbackPercentage;
        this.maxCashbackAmount = maxCashbackAmount;
    }

    public CashbackLoyaltyPlan(double cashbackPercentage, double maxCashbackAmount) {
        this.cashbackPercentage = cashbackPercentage;
        this.maxCashbackAmount = maxCashbackAmount;
    }

    public CashbackLoyaltyPlan() {
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
