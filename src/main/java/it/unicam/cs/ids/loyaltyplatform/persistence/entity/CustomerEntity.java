package it.unicam.cs.ids.loyaltyplatform.persistence.entity;

import jakarta.persistence.*;

import java.util.Date;
/*
TODO: -finire di implementare l'entità.
      -rivedere la relazione con la tessera
      -dovrebbe avere una lista di piani fedeltà? In caso affermativo aggiungere la relazione
      -rivedere costruttori, getter&setters
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
    private Date birthDate;

    @OneToOne
    @JoinColumn(name = "card_id")
    private CardEntity card;

    public CustomerEntity(String name, String surname, String emailAddress, String phoneNumber, Date birthDate) {
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

    public Date getBirthDate() {
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

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }
}


