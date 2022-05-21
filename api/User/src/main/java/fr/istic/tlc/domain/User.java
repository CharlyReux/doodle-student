package fr.istic.tlc.domain;

import java.nio.charset.StandardCharsets;

import javax.persistence.*;

import com.google.common.hash.Hashing;
import org.eclipse.microprofile.openapi.annotations.media.Schema;

import io.swagger.v3.oas.annotations.*;

@Entity
@Schema(name="User")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(readOnly = true)
    private Long id;

    @Column(name="username")
    private String username;
    @Column(name="email")
    private String email;
    @Column(name="password")
    private String password;

    public User(){}
    
    public User(String username, String email, String password){
        this.username=username;
        this.email=email;
        this.password=Hashing.sha256().hashString(password+"secret_pepper", StandardCharsets.UTF_8).toString();
    }

    public String getEmail(){
        return email;
    }

    public void setEmail(String email){
        this.email=email;
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
