package istic.tlc.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.eclipse.microprofile.openapi.annotations.media.Schema;


public class User {

    private Long id;

    private String username;
    private String mail;

    List<Choice> userChoices = new ArrayList<>();

    public User(){}

    public User(String username) {	
        this.username = username;
    }

    public void addChoice(Choice choice){
        this.userChoices.add(choice);
    }
    public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
	}

	public void removeChoice(Choice choice){
        this.userChoices.remove(choice);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public List<Choice> getUserChoices() {
        return userChoices;
    }

    public void setUserChoices(List<Choice> userChoices) {
        this.userChoices = userChoices;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
