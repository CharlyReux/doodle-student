package fr.istic.tlc.dao;

import javax.enterprise.context.ApplicationScoped;

import fr.istic.tlc.domain.comment;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class commentRepository implements PanacheRepository<comment>{
    
    /*public comment findByID(int id){
        return find("id",id).firstResult();
    }*/

}

