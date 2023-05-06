package it.unicam.cs.ids.loyaltyplatform.persistence.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/*
TODO: -controllare lista di loyaltyplans
      -relazione molti a molti con piano?
      -Lista di enrollments oppure di piani fedeltà?
 */
@Entity
@Table(name = "customer")
public class CustomerEntity {
    @Id
    //@GeneratedValue(strategy = GenerationType.AUTO)
    @SequenceGenerator(
            name = "customer_sequence",
            sequenceName = "customer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "customer_sequence"
    )
    private Integer id;
    private String name;
    private String surname;
    private String emailAddress;
    private String phoneNumber;
    private LocalDate birthDate;

    @OneToOne
    @JoinColumn(name = "card_id")
    private CardEntity card;

    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    @JoinTable(name = "customerEnrollment",
            joinColumns = @JoinColumn(name = "customer_id"),
            inverseJoinColumns = @JoinColumn(name = "plan_id"))
    private List<LoyaltyPlanEntity> loyaltyPlanList = new ArrayList<>();

    public CustomerEntity(String name, String surname, String emailAddress, String phoneNumber, LocalDate birthDate) {
        this.name = name;
        this.surname = surname;
        this.emailAddress = emailAddress;
        this.phoneNumber = phoneNumber;
        this.birthDate = birthDate;
    }

    public CustomerEntity() {
    }

    public Integer getId() {
        return id;
    }

    public List<LoyaltyPlanEntity> getLoyaltyPlanList() {
        return loyaltyPlanList;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

}


