package fr.istic.tlc.dao;

import javax.enterprise.context.ApplicationScoped;

import fr.istic.tlc.domain.dashboard;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class dashBoardRepository implements PanacheRepository<dashboard> {

    public dashboard findByMailUser(String mailUser){
        return find("mailUser",mailUser).firstResult();
    }
}
