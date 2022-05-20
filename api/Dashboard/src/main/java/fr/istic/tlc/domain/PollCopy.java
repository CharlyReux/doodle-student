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
public class PollCopy {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;


    private Long idTab;

    @NotBlank
    @Schema(title="Poll Title")
    private String title;

    private String location;
    private String description;

    private String urlSondage;
    private String urlSondageAdmin;

    private String slug ;
    private String slugAdmin ;

/* 	@JsonIgnore
    @ManyToMany(mappedBy = "adminPolls")
    List<Dashboard> dashboardListAd = new ArrayList<>();
 */
 /*    @JsonIgnore
    @ManyToMany(mappedBy = "userPolls")
    List<Dashboard> dashboardListUs = new ArrayList<>();
 */
    public PollCopy(){}

    public PollCopy(String title, String location, String description,String urlSondage,String urlSondageAdmin, String slugAdmin, String slug,Long idTab){
        this.title = title;
        this.location = location;
        this.description = description;
        this.urlSondage = urlSondage;
        this.urlSondageAdmin=urlSondageAdmin;
        this.slugAdmin = slugAdmin;
        this.slug = slug;
        this.idTab = idTab;
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

    public String getUrlSondageAdmin() {
        return urlSondageAdmin;
    }

    public void setUrlSondageAdmin(String urlSondageAdmin) {
        this.urlSondageAdmin = urlSondageAdmin;
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

    public void setSlugAdmin(String slugAdmin) {
        this.slugAdmin = slugAdmin;
    }

    public Long getIdTab() {
        return idTab;
    }

    public void setIdTab(Long idTab) {
        this.idTab = idTab;
    }

    
}
