package fr.istic.tlc.dao;

import javax.enterprise.context.ApplicationScoped;

import fr.istic.tlc.domain.Poll;
import io.quarkus.hibernate.orm.panache.PanacheRepository;

@ApplicationScoped
public class PollRepository implements PanacheRepository<Poll>{
    

}
