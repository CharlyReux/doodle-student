package istic.tlc.domain;

import java.sql.Date;
import java.util.List;



public class Poll {

    private Long id;

    private String title;

    private String location;
    private String description;

    private String urlSondage;
    private String urlSondageAd;

    private String slug = Utils.getInstance().generateSlug(24);
    private String slugAdmin = Utils.getInstance().generateSlug(24);
    private String tlkURL = "https://tlk.io/"+Utils.getInstance().generateSlug(12);
    public boolean clos = false;

    private String padURL;
    
    private Date createdAt;

    List<Choice> pollChoices;
 
    Choice selectedChoice;

    public Poll(){}

    public Poll(String title, String location, String description,String urlSondage,String urlSondageAd, List<Choice> pollChoices){
        this.title = title;
        this.location = location;
        this.description = description;
        this.urlSondage = urlSondage;
        this.urlSondageAd=urlSondageAd;
        this.pollChoices = pollChoices;    
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

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public List<Choice> getPollChoices() {
        return pollChoices;
    }

    public void setPollChoices(List<Choice> pollChoices) {
        this.pollChoices = pollChoices;
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

    public void setSlugAdmin(String slugAdmin) {
        this.slugAdmin = slugAdmin;
    }

    public boolean isClos() {
        return clos;
    }

    public void setClos(boolean clos) {
        this.clos = clos;
    }

    public Choice getSelectedChoice() {
        return selectedChoice;
    }

    public void setSelectedChoice(Choice selectedChoice) {
        this.selectedChoice = selectedChoice;
    }

    public String getTlkURL() {
        return tlkURL;
    }

    public String getPadURL() {
        return this.padURL;
    }
    
}
