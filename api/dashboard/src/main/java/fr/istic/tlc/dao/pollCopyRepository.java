package fr.istic.tlc.dao;

import javax.enterprise.context.ApplicationScoped;

import fr.istic.tlc.domain.pollCopy;
import io.quarkus.hibernate.orm.panache.PanacheRepository;



@ApplicationScoped
public class pollCopyRepository implements PanacheRepository<pollCopy>  {
    
}
