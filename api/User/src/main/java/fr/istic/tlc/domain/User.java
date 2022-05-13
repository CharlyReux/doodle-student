package fr.istic.tlc.domain;

import javax.persistence.*;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String mail;
    private String password;

    public User(){}

    public String getMail(){
        return mail;
    }

    public void setMail(String mail){
        this.mail=mail;
    }

    public Long getId(){
        return this.id;
    }

    public void setUsername(String usernameString ){
        this.username=usernameString;
    }

    public String getUsername(){
        return this.username;
    }
    
}
