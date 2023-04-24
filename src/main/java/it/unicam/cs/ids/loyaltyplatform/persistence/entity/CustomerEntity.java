package it.unicam.cs.ids.loyaltyplatform.persistence.entity;

import jakarta.persistence.*;

import java.util.Date;
//TODO sistemare la relazione con la tessera alla riga 17
public class CustomerEntity {
    @Id
    private Integer id;
    private String name;
    private String surname;
    private String emailAddress;
    private String phoneNumber;
    private Date birthDate;

    @OneToOne(mappedBy = "cliente")
    private CardEntity card;
    public CustomerEntity(Integer id, String name, String surname, String emailAddress, String phoneNumber, Date birthDate) {
        this.id = id;
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
}


