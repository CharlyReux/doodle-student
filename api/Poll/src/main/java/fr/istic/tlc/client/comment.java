package fr.istic.tlc.client;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

public class comment {
    
    @Schema(readOnly = true)
    public Long id;

    public Long pollID;

    public String content;

    public String auteur;

    public Long getPollID() {
        return pollID;
    }

    public void setPollID(Long pollID) {
        this.pollID = pollID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    
}
