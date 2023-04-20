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

    public CompanyEntity(Integer id, String name, String emailAddress) {
        this.id = id;
        this.name = name;
        this.emailAddress = emailAddress;
    }

    public CompanyEntity() {
    }

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
