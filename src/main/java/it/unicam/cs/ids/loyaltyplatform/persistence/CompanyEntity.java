package it.unicam.cs.ids.loyaltyplatform.persistence;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
//import jakarta.persistence.Table;

@Entity
//@Table
public class CompanyEntity {
    @Id
    private Integer id;
    private String name;
    private String emailAddress;

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getEmailAddress() {
        return emailAddress;
    }
}
