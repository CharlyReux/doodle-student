package fr.istic.tlc.domain;

import java.nio.charset.StandardCharsets;

import javax.persistence.*;

import com.google.common.hash.Hashing;
@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String username;
    private String mail;
    private String password;

    public User(){}
    
    public User(String username, String mail, String password){
        this.username=username;
        this.mail=mail;
        this.password=Hashing.sha256().hashString(password+"secret_pepper", StandardCharsets.UTF_8).toString();
    }

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
    
    public String getPassword(){
        return this.password;
    }

    public void setPassword(String password){
        this.password=Hashing.sha256().hashString(password+"secret_pepper", StandardCharsets.UTF_8).toString();
    }
}
