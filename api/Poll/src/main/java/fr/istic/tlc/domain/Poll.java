package fr.istic.tlc.domain;

import javax.persistence.*;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.eclipse.microprofile.openapi.annotations.media.Schema;


@Entity
@Schema(name="Poll",description="Poll representation to create")
public class Poll {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @NotBlank
    @Schema(title="Poll Title")
    private String title;

    public void setTitle(String title) {
        this.title = title;
    }

    public Poll(){}

    public Poll(String title){
        this.title=title;
    }

    public String getTitle(){
        return title;
    }

    public Long getId(){
        return id;
    }
}
