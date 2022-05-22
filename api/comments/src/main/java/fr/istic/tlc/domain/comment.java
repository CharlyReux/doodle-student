package fr.istic.tlc.domain;


import javax.persistence.*;

import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;

import org.eclipse.microprofile.openapi.annotations.media.Schema;


@Entity
@Schema(name="comment",description="comment representation to create")
public class comment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(readOnly = true)
    private Long id;

    @NotBlank
    @Schema(title="poll id")
    private Long pollID;

    @NotBlank
    @Schema(title="comment author")
    private String auteur;

    @NotBlank
    @Schema(title="comment content")
    private String content;





    public comment(){}

    public comment(String content,String auteur,Long pollID){
        this.content=content;
        this.auteur = auteur;
        this.pollID = pollID;
    }

    public String getcontent(){
        return content;
    }
    
    public void setcontent(String content) {
        this.content = content;

    }

    public Long getId(){
        return id;
    }

    public String getAuteur() {
        return this.auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }


    public Long getPollID() {
        return this.pollID;
    }

    public void setPollID(Long pollID) {
        this.pollID = pollID;
    }
}
