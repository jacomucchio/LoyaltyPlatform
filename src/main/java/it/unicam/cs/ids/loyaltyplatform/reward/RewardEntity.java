package it.unicam.cs.ids.loyaltyplatform.reward;

import com.fasterxml.jackson.annotation.JsonIgnore;
import it.unicam.cs.ids.loyaltyplatform.enrollment.PointEnrollment;
import it.unicam.cs.ids.loyaltyplatform.loyaltyPlan.PointLoyaltyPlan;
import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.validation.annotation.Validated;
import java.util.List;
import java.math.BigDecimal;
import java.time.LocalDate;

@Entity
@Validated
@Table(name = "reward")
public class RewardEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @NotNull
    @DecimalMin(value = "0.01", message = "Discount percentage must be more than 0")
    @DecimalMax(value = "100", message = "Discount percentage must be less then 100")
    private BigDecimal discountPercentage;

    @NotNull
    @DecimalMin(value = "1", message = "Required points must be more than 1")
    private Integer requiredPoints;

    @NotNull
    @Future(message = "Expiration date cannot be in the past")
    private LocalDate expirationDate;

    @ManyToOne
    @JoinColumn(name = "loyalty_plan_id")
    @JsonIgnore
    private PointLoyaltyPlan pointLoyaltyPlan;

    @ManyToMany(mappedBy = "obtainedRewards", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<PointEnrollment> redeemedByCustomer;
    public RewardEntity(@NotNull BigDecimal discountPercentage, @NotNull Integer requiredPoints, @NotNull LocalDate expirationDate) {
        this.discountPercentage = discountPercentage;
        this.expirationDate = expirationDate;
        this.requiredPoints = requiredPoints;
    }

    public RewardEntity() {

    }

    public Integer getId() {
        return id;
    }

    public BigDecimal getDiscountPercentage() {
        return discountPercentage;
    }

    public LocalDate getExpirationDate() {
        return expirationDate;
    }
    public PointLoyaltyPlan getPointLoyaltyPlan() {
        return pointLoyaltyPlan;
    }

    public void setPointLoyaltyPlan(PointLoyaltyPlan pointLoyaltyPlan) {
        this.pointLoyaltyPlan = pointLoyaltyPlan;
    }

    public Integer getRequiredPoints() {
        return requiredPoints;
    }

    public void setRequiredPoints(Integer requiredPoints) {
        this.requiredPoints = requiredPoints;
    }
}

