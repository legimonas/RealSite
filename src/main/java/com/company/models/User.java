package com.company.models;

import org.springframework.security.crypto.bcrypt.BCrypt;

import java.time.LocalDate;

public class User {
    
    
    Integer id;
    private String Name;
    private String Password;
    private LocalDate birthdate;
    private String UserMail;

    public User(Integer id, String name, String password, LocalDate birthdate, String userMail) {
        this.id = id;
        Name = name;
        Password = password;
        this.birthdate = birthdate;
        UserMail = userMail;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        this.Password = password;
    }

    public void hashPassword(){
        this.Password = BCrypt.hashpw(this.Password, BCrypt.gensalt());
    }


    public LocalDate getBirthdate() {
        return birthdate;
    }

    public void setBirthdate(LocalDate birthdate) {
        this.birthdate = birthdate;
    }

    public String getUserMail() {
        return UserMail;
    }

    public void setUserMail(String userMail) {
        UserMail = userMail;
    }
}
