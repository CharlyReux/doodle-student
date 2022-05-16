package fr.istic.tlc.domain;


import javax.persistence.*;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.eclipse.microprofile.openapi.annotations.media.Schema;


@Entity
@Schema(name="comment",description="comment representation to create")
public class comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @JsonIgnore
    private Long id;

    @NotBlank
    @Schema(title="comment content")
    private String content;

    public void setcontent(String content) {
        this.content = content;
    }

    public comment(){}

    public comment(String content){
        this.content=content;
    }

    public String getcontent(){
        return content;
    }

    public Long getId(){
        return id;
    }
}
