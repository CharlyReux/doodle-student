package fr.istic.tlc.dao;

import javax.enterprise.context.ApplicationScoped;

import fr.istic.tlc.domain.Dashboard;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class dashBoardRepository implements PanacheRepository<Dashboard> {
    
    public Dashboard findByIDUser(Long idUser){
        return find("idUser",idUser).firstResult();
    }
}
