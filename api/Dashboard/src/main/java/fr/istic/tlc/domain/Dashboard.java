package fr.istic.tlc.domain;

import java.util.List;

import javax.persistence.*;

import org.eclipse.microprofile.openapi.annotations.media.Schema;

@Entity
@Schema(name="dashboard",description="dashboard representation to create")
public class Dashboard {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Schema(readOnly = true)
    private Long id;

    Long idUser;
/* 
    @ManyToMany
    @JoinTable(
            name = "dashboard_pollCopyAdmin",
            joinColumns = @JoinColumn(name = "dashboardAdmin_id"),
            inverseJoinColumns = @JoinColumn(name = "pollCopyAdmin_id"))
    List<PollCopy> adminPolls;
 */
/*     @ManyToMany
    @JoinTable(
            name = "dashboard_pollCopyUser",
            joinColumns = @JoinColumn(name = "dashboardUser_id"),
            inverseJoinColumns = @JoinColumn(name = "pollCopyUser_id"))
    @OrderBy("id ASC")
    List<PollCopy> userPolls;
 */
    public Dashboard(){}

    public Dashboard(Long idUser, List<PollCopy> adminPolls,List<PollCopy> userPolls){
        this.idUser = idUser;
        /* this.adminPolls=adminPolls; */
       /*  this.userPolls=userPolls; */
    }

    public Long getIdUser() {
        return idUser;
    }

    public void setIdUser(Long idUser) {
        this.idUser = idUser;
    }
/* 
    public List<PollCopy> getAdminPolls() {
        return adminPolls;
    }

    public void setAdminPolls(List<PollCopy> adminPolls) {
        this.adminPolls = adminPolls;
    } */

/*     public List<PollCopy> getUserPolls() {
        return userPolls;
    }

    public void setUserPolls(List<PollCopy> userPolls) {
        this.userPolls = userPolls;
    }
 */
  /*   public void addInAdmin(PollCopy p){
        this.adminPolls.add(p);
    } */

/*     public void addInUser(PollCopy p){
        this.userPolls.add(p);
    } */
    
}
