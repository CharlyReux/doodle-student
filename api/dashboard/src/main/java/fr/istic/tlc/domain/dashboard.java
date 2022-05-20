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


    Long idUser;

/*     @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(name="dashboard_pollCopy",
    joinColumns = @JoinColumn(name="dashboard_id"),
    inverseJoinColumns = @JoinColumn(name="pollCopy_id")) */

    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pollCopyAdID")
    private List<pollCopy> adPolls;

/*     @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "dashboard_pollCopyUser",
            joinColumns = @JoinColumn(name = "dashboardUser_id"),
            inverseJoinColumns = @JoinColumn(name = "pollCopyUser_id")) */
    
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "pollCopyUsID")
    List<pollCopy> userPolls;
 
    public dashboard(){}

    public dashboard(Long idUser, List<pollCopy> adPolls,List<pollCopy> userPolls){
        this.idUser = idUser;
        this.adPolls=adPolls;
        this.userPolls=userPolls;
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
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
