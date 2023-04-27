package it.unicam.cs.ids.loyaltyplatform.model;
//TODO controllare il costruttore che parametri deve avere. Company????
public class LoyaltyPlanDto {
    private String name;
    private int customerCount;
    private Integer companyId;

    public LoyaltyPlanDto(String name, Integer companyId) {
        this.name = name;
        this.customerCount = 0;
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public int getCustomerCount() {
        return customerCount;
    }

    public Integer getCompanyId() {
        return companyId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCustomerCount(int customerCount) {
        this.customerCount = customerCount;
    }

    public void setCompanyId(Integer companyId) {
        this.companyId = companyId;
    }
}
