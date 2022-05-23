package istic.tlc.domain;

import java.util.ArrayList;
import java.util.List;




public class User {

    private Long id;

    private String username;
    private String mail;

    public User(){}

    public User(String username) {	
        this.username = username;
    }

    public String getMail() {
		return mail;
	}

	public void setMail(String mail) {
		this.mail = mail;
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

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", username='" + username + '\'' +
                '}';
    }
}
