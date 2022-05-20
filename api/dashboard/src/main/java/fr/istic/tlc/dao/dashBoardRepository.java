package fr.istic.tlc.dao;

import javax.enterprise.context.ApplicationScoped;

import fr.istic.tlc.domain.dashboard;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class dashBoardRepository implements PanacheRepository<dashboard> {
    
    public dashboard findByIDUser(Long idUser){
        return find("idUser",idUser).firstResult();
    }
}
