package fr.istic.tlc.domain;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.*;

import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonIgnore;

import org.eclipse.microprofile.openapi.annotations.media.Schema;


/**
 * This is a simplified copy of the class poll so that we can make associations between the user and the polls
 */
@Entity
@Schema(name="Poll",description="Poll representation to create")
public class pollCopy {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(readOnly = true)
    private Long id;



    @NotBlank
    @Schema(title="Poll Title")
    private String title;

    private String location;
    private String description;

    private String urlSondage;
    private String urlSondageAd;

    private String slug ;
    private String slugAdmin ;

    public pollCopy(){}

    public pollCopy(String title, String location, String description,String urlSondage,String urlSondageAd, String slugAdmin, String slug){
        this.title = title;
        this.location = location;
        this.description = description;
        this.urlSondage = urlSondage;
        this.urlSondageAd=urlSondageAd;
        this.slugAdmin = slugAdmin;
        this.slug = slug;
    }


    public String getTitle(){
        return title;
    }

    public Long getId(){
        return id;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrlSondage() {
        return urlSondage;
    }

    public void setUrlSondage(String urlSondage) {
        this.urlSondage = urlSondage;
    }

    public String getUrlSondageAd() {
        return urlSondageAd;
    }

    public void setUrlSondageAd(String urlSondageAd) {
        this.urlSondageAd = urlSondageAd;
    }

    public String getSlug() {
        return slug;
    }

    public void setSlug(String slug) {
        this.slug = slug;
    }

    public String getSlugAdmin() {
        return slugAdmin;
    }

    public void setSlugAd(String slugAdmin) {
        this.slugAdmin = slugAdmin;
    }

    
}
