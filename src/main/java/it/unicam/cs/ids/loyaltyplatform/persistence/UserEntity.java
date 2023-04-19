package it.unicam.cs.ids.loyaltyplatform.persistence;

import jakarta.persistence.Id;

public class UserEntity {
    @Id
    private Integer id;
    private String name;

    private String surname;
    private String emailAddress;
    private String phoneNumber;
    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    public String getSurname() {return surname; }
    public String getEmailAddress() {
        return emailAddress;
    }
    public String getPhoneNumber() {return phoneNumber; }
}


