package fr.istic.tlc.domain;

import java.util.List;

import javax.persistence.*;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
@Schema(name="dashboard",description="dashboard representation to create")
public class dashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(readOnly = true)
    private Long id;

    String mailUser;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pollCopyAdID")
    private List<pollCopy> adPolls;

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pollCopyUsID")
    List<pollCopy> userPolls;
 
    public dashboard(){}

    public dashboard(String mailUser, List<pollCopy> adPolls,List<pollCopy> userPolls){
        this.mailUser = mailUser;
        this.adPolls=adPolls;
        this.userPolls=userPolls;
    }
    

    public String getMailUser() {
        return mailUser;
    }

    public void setMailUser(String mailUser) {
        this.mailUser = mailUser;
    }

    public List<pollCopy> getadPolls() {
        return adPolls;
    }

    public void setadPolls(List<pollCopy> adPolls) {
        this.adPolls = adPolls;
    }

    public List<pollCopy> getUserPolls() {
        return userPolls;
    }

    public void setUserPolls(List<pollCopy> userPolls) {
        this.userPolls = userPolls;
    }

    public void addInAdmin(pollCopy p){
        this.adPolls.add(p);
    }

    public void addInUser(pollCopy p){
        this.userPolls.add(p);
    }
    
}
